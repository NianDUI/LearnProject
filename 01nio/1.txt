package top.niandui.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/*
    一，缓冲区（buffer），就是数组，用于存储不同数据类型的数据

    根据数据类型的不同(除了boolean)，提供了相应类型的缓冲区。
    ByteBuffer
    CharBuffer
    ShortBuffer
    IntBuffer
    LongBuffer
    FloatBuffer
    DoubleBuffer

    上述缓冲区的管理方式几乎一致。通过 allocate() 获取缓冲区。

    二、  缓冲区存储数据的两个核心方法。
    put() : 存入数据到缓冲区中。
    get() : 获取缓冲区中的数据。

    三、 缓冲区中的四个核心属性。
    capacity : 容量。表示缓冲中最大存储数据的容量。。一旦声明不能改变。
    limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
    position : 位置，表示缓冲区中正在操作数据的位置。

    mark : 标记，表示记录当前position 的位置。可以通过reset() 回复到mark 的位置。

    mark <= position <= limit <= capacity
    mark = -1

    常用方法：
        // 1.分配一个指定大小的缓冲区 ByteBuffer.allocate(1024)
        // 2. put() 存入数据
        // 3. flip() 切换成读取数据模式
        // 4. get() 取出数据
        // 5. rewind() : 可重复读数据，读重置
        // 6. clear() : 清空缓冲区。但是缓冲区中的数据依然存在。但是过于”被遗忘“状态。
        // mark() 标记一下，当前position 的位置
        // reset() 让position 回复到mark 的位置
        // hasRemaining() 判断缓冲区中是否还有剩余数据
        // remaining() 获取缓冲区中可以操作的数量

        // array() 将缓冲区转换成数组


    五.  直接缓冲区与非直接缓冲区。
    非直接缓冲区 ：通过allocate() 方法分配缓冲区，将缓冲区建立在JVM的内存中。
    直接缓冲区   ：通过allocateDirect() 方法分配缓冲区，将缓冲区建立在物理的内存中。可以提高效率的。
        // isDirect() 确定是否是直接缓冲区。



 */
public class Test01Buffer {

    @Test
    public void test3() {
        // 分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        System.out.println(buf.isDirect());
    }

    @Test
    public void test2() {
        String str = "abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());

        // mark() 标记一下
        buf.mark();

        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());

        // reset() 回复到mark的位置
        buf.reset();
        System.out.println(buf.position());

        // hasRemaining() 判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()) {
            // remaining() 获取缓冲区中可以操作的数量
            System.out.println(buf.remaining());
        }


    }

    @Test
    public void test1() {
        String str = "abcde";
        // 1.分配一个指定大小的缓冲区 ByteBuffer.allocate(1024)
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("---------allocate()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());


        // 2. put() 存入数据
        buf.put(str.getBytes());

        System.out.println("---------put()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 3. flip() 切换成读取数据模式
        buf.flip();

        System.out.println("---------flip()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 4. get() 取出数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("---------get()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 5. rewind() : 可重复读数据，读重置
        buf.rewind();

        System.out.println("---------rewind()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 6. clear() : 清空缓冲区。但是缓冲区中的数据依然存在。但是过于”被遗忘“状态。
        buf.clear();

        System.out.println("---------clear()---------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println((char) buf.get());
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());


    }

}
