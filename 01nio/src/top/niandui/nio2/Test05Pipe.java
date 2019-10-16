package top.niandui.nio2;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Pipe用于线程间通信
 *  有两条单向的组成
 *      SinkChannel : 发送
 *      SourceChannel : 接受
 */
public class Test05Pipe {

    @Test
    public void test1() throws IOException {
        // 1. 获取管道
        Pipe pipe = Pipe.open();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("通过单向管道传输数据".getBytes());

        // 2. 将缓冲区中的数据写入管道
        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.flip();
        sinkChannel.write(buf);

        // 3. 读取缓冲区中的数据
        buf.clear();
        Pipe.SourceChannel sourceChannel = pipe.source();
        sourceChannel.read(buf);
        System.out.println(new String(buf.array(), 0, buf.limit()));

        sinkChannel.close();
        sourceChannel.close();
    }

}
