package com.han.login;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by hanminggui on 7/5/2016.
 */
public class Master {
    public static void main(){
        ServerSocket server = null;

        try {
            server = new ServerSocket(6666);

            while(true){
                new Dog(server.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
