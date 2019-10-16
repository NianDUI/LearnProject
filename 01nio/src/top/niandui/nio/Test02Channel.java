package top.niandui.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 * 一、 通道（channel）：用户源节点与目标节点的链接， 在 Java NIO 中负责缓冲区中数据的传输。
 *          Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 *      Java.nio.channels.Channel接口 ：
 *          |-- FileChannel
 *          |-- SocketChannel
 *          |-- ServerSocketChannel
 *          |-- DatagramChannel
 *
 * 三、 获取通道
 *      1. Java 针对支持通道的类提供了 getChannel() 方法。
 *          本地 IO：
 *          FileInputStream/FileOutputStream
 *          RandomAccessFile (随机存储文件流)
 *
 *          网络 IO：
 *          Socket
 *          ServerSocket
 *          DatagramSocket
 *
 *      2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 *      3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 四、 通道之间的数据传输
 *      transferFrom() 从哪个通道传递
 *      transferTo() 传递到那个通道去
 *
 * 五、 分散( Scatter )与聚集( Gather )
 *      分散读取（Scattering Reads） ：将通道中的数据分散到多个缓冲区中        读到缓冲区数组中
 *      聚集写入（Gathering Writes） ：将多个缓冲区中的数据聚集到通道中        从缓冲区数组中写
 *
 *              -> Buffer ->
 *      Channel -> Buffer -> Channel
 *              -> Buffer ->
 *          分散读取     聚集写入
 *
 * 六、 字符集：Charset
 *      编码 ： 字符串 -> 字符数组
 *      解码 ： 字符数组 -> 字符串
 *
 */
public class Test02Channel {

    // 6. 字符集：Charset
    @Test
    public void test6() throws IOException {
        Charset cs1 = Charset.forName("GBK");

        // 获取编码器
        CharsetEncoder ce = cs1.newEncoder();

        // 获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("编码与解码！");
        cBuf.flip();

        // 编码
        ByteBuffer bBuf = ce.encode(cBuf);
        System.out.println(bBuf.position()); // 0

        for (int i = 0; i < bBuf.limit(); i++) {
            System.out.println(bBuf.get());
        }

        System.out.println(bBuf.position()); // 12
        bBuf.flip();

        // 解码
        CharBuffer cBuf2 = cd.decode(bBuf);

        System.out.println(cBuf2.toString()); // 编码与解码！


        System.out.println("-----------------");

        Charset cs2 = Charset.forName("utf-8");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3); // ��������룡

    }

    @Test
    public void test5() {
        // NIO 中支持的字符集
        Map<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

    }

    // 5. 分散( Scatter )与聚集( Gather )
    @Test
    public void test4() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");

        // 1. 获取通道
        FileChannel channel1 = raf1.getChannel();

        // 2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        // 3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip(); // 这一步不许得有
        }
//        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
//        System.out.println("------------------");
//        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        // 4. 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufs);

        channel1.close();
        channel2.close();
        raf1.close();
        raf2.close();
    }


    // 3. 通道之间的数据传输(直接缓冲区的方式)
    @Test
    public void test3 () throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("./", "1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);

        // transferTo() 传递到那个通道去
//        inChannel.transferTo(0, inChannel.size(), outChannel);
        // transferFrom() 从哪个通道传递
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    // 2. 使用直接缓冲区完成文件的复制（内存映射文件）。
    @Test
    public void test2() throws IOException { // 耗费时间：26 22 18 17
        // 方式2
        long start = System.currentTimeMillis();

        // public static FileChannel open(Path path, OpenOption... options)
        // Path ： 表示路径
        // OpenOption ： 想要做什么操作
        FileChannel inChannel = FileChannel.open(Paths.get("./", "1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);

        // 内存映射文件(只有ByteBuffer支持)
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        // java.nio.channels.NonReadableChannelException
        // outMappedBuf 是读写模式，所以需要在 outChannel 中加上读的权限

        // 直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));

    }

    // 1. 利用通道完成文件的复制（非直接缓冲区）。
    @Test
    public void test1() { // 耗费时间：56 44 41 42
        // 方式1
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");

            // 1. 获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            // 2. 分配一个指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 3. 将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1) {
                buf.flip(); // 切换成读数据的模式
                // 4. 将缓冲区的数据写入缓冲区中
                outChannel.write(buf);
                // 清空缓冲区
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // 5. 关闭通道，关闭流
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));
    }
}
