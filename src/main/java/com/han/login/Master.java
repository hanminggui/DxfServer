package com.han.login;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by hanminggui on 7/5/2016.
 */
public class Master {
    public static void main(String args[]){
        System.out.println("server start ok");
        ServerSocket server = null;
        try {
            server = new ServerSocket(6666);
            while(true){
                new Thread(new Dog(server.accept())).run();//并发量少的时候不用线程池
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
