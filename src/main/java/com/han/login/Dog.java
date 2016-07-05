package com.han.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端连接步骤
 * 1、发送三个秘钥进行验证是否是客户端
 * 2、发送账号
 * 3、发送密码
 * 4、返回UID
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
     *初始化
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
        System.out.println(client.getInetAddress() +":"+ client.getPort() + "已连接");
    }

    /**
     *线程入口
     */
    @Override
    public void run() {
        if (this.assertKey()){
            try {
                user = br.readLine();
                pwd = br.readLine();
                int uid = DB.getUID(user, pwd);
                pw.write(uid);//返回uid
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            pw.write("请更新客户端版本");
        }
        this.close();

        System.out.println(client.getInetAddress() +":"+ client.getPort() + "的请求已处理完成");
    }

    /**
     * 验证秘钥区分是否是客户端登录 或者版本
     * @return
     */
    private boolean assertKey(){
        String tmp = null;
        for(int i=0; i<this.key.length; i++){
            try{
                tmp = br.readLine();
                if(!(tmp.equals(key[i]))){
                    System.out.println("秘钥验证失败" + key[i] + "    " + tmp);
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     *关闭当前线程资源
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
