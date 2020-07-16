package com.bosssoft.hr.train.socket;

/**
 * @param
 * @description:
 * @author: Administrator
 * @create: 2020-05-28 22:17
 * @since
 **/
public class ClientSocketApplication {
    public static  void main(String[] args){
        Starter clientSocket=new ClientSocket();
        clientSocket.start();
    }
}

