package com.han.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by hanminggui on 7/5/2016.
 */
public class Dog implements Runnable{


    private Socket client = null;
    private PrintWriter pw = null;
    private BufferedReader br = null;
    private String user = null;
    private String pwd = null;
    private String key[] = new String[]{"key1", "key2", "key3"};
    /**
     *
     * @param client
     */
    public Dog(Socket client){
        this.client = client;
        try {
            pw = new PrintWriter(client.getOutputStream());
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        if (this.assertKey()){
            try {
                user = br.readLine();
                pwd = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {

        }


    }

    private void checkUser(){

    }

    /**
     * 验证秘钥区分是否是客户端登录 或者版本
     * @return
     */
    private boolean assertKey(){
        for(int i=0; i<this.key.length; i++){
            try{
                if(!this.br.readLine().equals(key[i])){
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public void x(int flag){
        switch (flag){
            case 0 :
                break;
            case 1 :
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    public void close(){
        if(this.br != null){
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                br = null;
            }
        }
        if(this.pw != null){
            pw.flush();
            pw.close();
            this.pw = null;
        }
        if(this.client != null){
            try {
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
                this.client = null;
            }
        }

    }

}
