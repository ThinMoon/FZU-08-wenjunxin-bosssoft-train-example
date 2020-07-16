package com.bosssoft.hr.train.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @param
 * @author: Administrator
 * @create: 2020-05-28 22:25
 * @since
 **/
@Slf4j
public class ClientSocket implements  Starter{
    @Override
    public boolean start() {

        SocketChannel socketChannel;

        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
            //非阻塞
            socketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //给server发数据
            buffer.put("Hello Server".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();

            //接收server数据
            int len = 0; //实际读取的字符数
            while ((len = socketChannel.read(buffer)) != -1) {
                buffer.flip();
                String context = new String(buffer.array(), 0, len);
                log.info(context);
                buffer.clear();
            }
            return true;


        } catch (IOException e) {
            log.error("与服务器连接失败：{}", e.toString());
        }


        return false;
    }
}

