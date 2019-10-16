package top.niandui.nio2;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.rmi.ServerError;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 一、 使用 NIO 完成网络通信的三个核心
 *
 *  1. 通道（Channel） ： 负责连接
 *
 *      java.nio.channel.Channel 接口：
 *          |-- SelectableChannel
 *              |-- socketChannel
 *              |-- ServerSocketChannel
 *              |-- DatagramChannel
 *
 *              |-- Pipe.SinkChannel
 *              |-- Pipe.SourceChannel
 *
 *  2. 缓冲区（Buffer） ： 负责数据的存取
 *
 *  3. 选择器（Selector） ： 是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况。
 *
 *      // 切换到非阻塞模式
 *          channel.configureBlocking(false);
 *
 *  注册事件：
 *      SelectionKey : 表示 SelectableChannel 和 Selector 之间的注册关系（事件）
 *      ssChannel.register(Selector sel, int ops) -> ops 指的就是 SelectionKey
 *          读 ： SelectionKey.OP_READ(1)
 *          写 ： SelectionKey.OP_WRITE(4)
 *          连接 ： SelectionKey.OP_CONNECT(8)
 *          接受 ： SelectionKey.OP_ACCEPT(16)
 *      注册多个事件，可以使用“位或”操作符连接。
 *          int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
 *
 *       selector.select() // 准备就绪的数量
 *
 *
 *       //切换为写事件
 *          selectionKey.interestOps(SelectionKey.OP_WRITE);
 *
 */
public class Test03NonBlockingNIO {
    // 非阻塞式，

    @Test
    public void client() throws IOException {
        // 1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // 2. 切换到非阻塞模式
        sChannel.configureBlocking(false);

        // 3. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 4. 发送数据给服务端
//        Scanner scan = new Scanner(System.in);
//
//        while (scan.hasNext()) {
//            String str = scan.next();
//            buf.put((new Date().toString() + "\n" + str).getBytes());
//            buf.flip();
//            sChannel.write(buf);
//            buf.clear();
//        }
        buf.put((new Date().toString()).getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();

        // 5. 关闭通道
        sChannel.close();

    }

    @Test
    public void server() throws IOException {
        // 1. 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        // 2. 切换到非阻塞模式
        ssChannel.configureBlocking(false);

        // 3. 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        // 4. 获取选择器
        Selector selector = Selector.open();

        // 5. 将通道注册到选择器上，并且指定“监听事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 6. 轮训式的获取选择器上已经“准备就绪”的事件
        int i = 0;
        while (selector.select() > 0) { // select 无满足条件的等待
            i++;
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            // 15. 取消选择键 ： SelectionKey
            it.remove();

            // 7. 获取当前选择器中所有注册的“选择键（已就绪的监听时间）”
            int j = 0;
            while (it.hasNext()) {
                j++;
                System.out.print(i + " -> " + j);

                // 8. 获取准备“就绪”的事件
                SelectionKey sk = it.next();

                // 9. 判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    System.out.println(" sk.isAcceptable()");

                    // 10.  若“准备就绪”，获取客户端连接
//                    SocketChannel sChannel = ssChannel.accept();
                    SocketChannel sChannel = ((ServerSocketChannel) sk.channel()).accept();

                    // 11. 切换非阻塞模式
                    sChannel.configureBlocking(false);

                    // 12. 将通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    System.out.println(" sk.isReadable()");

                    // 13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    // 14. 读数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    while (sChannel.read(buf) != -1) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, buf.limit()));
                        buf.clear();
                    }
                    // 判断远程通道是否关闭，关闭了就close本通道， 否则会一直执行
                    if (!sk.isConnectable()) {
//                        sk.cancel(); //取消选择器对该通道的注册
                        //关闭客户端对应的SocketChannel
                        sChannel.close();
                    }
                }
            }

        }


    }



}
