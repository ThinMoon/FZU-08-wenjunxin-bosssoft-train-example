package com.bosssoft.hr.train.socket;

/**
 * @param
 * @description:
 * @author: Administrator
 * @create: 2020-05-28 22:16
 * @since
 **/
public class ServerSocketApplication {
    public static  void main(String[] args){
        Starter serverSocket=new NIOServerSocket();
        serverSocket.start();
    }
}

