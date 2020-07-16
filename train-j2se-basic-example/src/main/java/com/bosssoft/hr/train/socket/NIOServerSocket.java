package com.bosssoft.hr.train.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @description:
 * @author: Administrator
 * @create: 2020-05-28 22:25
 * @since
 **/
@Slf4j
public class NIOServerSocket implements Starter{

    /**
     * Selector是网络编程NIO中的核心组件
     * Selector(选择器)这个组件用于采集各个通道的状态(事件)。
     * Selector轮询每个注册的Channel，一旦发现Channel有注册的事件发生，便获取事件然后进行处理。
     * Selector允许单线程处理多个Channel(一个Channel就是一个连接)，如果每个连接的流量都很小，使用Selector就会很方便
     *
     */
    Selector selector;


    /**
     * Channel是一个对象，可以通过它读取和写入数据。
     * 基本上，所以的IO在NIO中都是从一个Channel开始。
     * Channel有点像流（Stream），数据可以从Channel读取到Buffer，也可以从Buffer写到Channel。
     * 所有数据都通过Buffer对象处理！
     */
    ServerSocketChannel serverSocketChannel;

    /**
     * Buffer也是一个对象，它包含一些要写入或读出的数据，像是NIO读写数据的中转池。
     * 在NIO中，数据是放入buffer对象的，而在IO中，数据是直接写入或者读到Stream对象的。
     * 应用程序不能直接对 Channel 进行读写操作，
     * 而必须通过 Buffer 来进行，即 Channel 是通过 Buffer 来读写数据的。
     *
     */
    ByteBuffer buffer;


    @Override
    public boolean start() {

        try {
            //创建一个selector
            selector = Selector.open();

            /*------初始化tcp连接监听通道----------*/
            //创建serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //绑定端口号8888
            serverSocketChannel.bind(new InetSocketAddress(8888));
            //注册为非阻塞 此步必不可少
            serverSocketChannel.configureBlocking(false);

            /*--------将channel注册到selector 设置需要监听的事件----------*/
            /*
             * Accept：有可以接受的连接
             * Connect：连接成功
             * Read：有数据可读
             * Write：可以写入数据了
             */
            //设置为accept即可
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


            while (true) {
                //该方法会一直阻塞 直到有监听事件到来 不用担心cpu空转
                selector.select();

                /*一但调用select方法并且返回了，
                说明有一个或多个通道就绪了，
                然后通过该方法选择已经就绪的集合，
                然后遍历这些集合对每个通道进行处理。*/
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();

                    //判断是否为连接事件
                    if (selectionKey.isAcceptable()) {
                        //如果是连接事件 创建一个新的管道与该客户端进行通信
                        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                        //为该管道配置非阻塞
                        socketChannel.configureBlocking(false);
                        //注册selector
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        log.info("成功连接上:{}", socketChannel.getRemoteAddress());
                    //判断是否是可读事件
                    } else if (selectionKey.isReadable()) {
                        //获取到产生该事件的管道
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        /*------------创建缓冲区----------*/
                        buffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = socketChannel.read(buffer)) > 0) {

                            buffer.flip();
                            String context = new String(buffer.array(), 0, len);
                            log.info(context);
                            buffer.clear();
                        }
                        //回复一句 Hello Client
                        buffer.put("Hello Client".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);

                        socketChannel.close();

                    }

                    //已经处理的事件需要手动移除
                    iterator.remove();
                }
            }


        } catch (IOException e) {
            log.error("socket 连接失败：{}", e);
        } finally {
            try {
                serverSocketChannel.close();
                selector.close();
            } catch (IOException e) {
                log.error("socket 关闭异常：{}", e);
            }

        }

        return false;
    }
}

