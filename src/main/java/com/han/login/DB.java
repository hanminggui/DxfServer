package com.han.login;

import com.han.util.JDBC;
import com.han.util.Proper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by hanminggui on 7/5/2016.
 */
public class DB {
    private static Properties jdbc =  new Proper().getProperties("jdbc.properties");
    private static Properties sql =  new Proper().getProperties("sql.properties");

    /**
     * 账号密码验证通过时返回 UID
     * 账号不存在时返回 0
     * 密码错误时返回 -1
     * @param user 用户名
     * @param pass 密码
     * @return
     */
    public static int getUID(String user, String pass){
        int uId = 0;
        try {
            Class.forName(jdbc.getProperty("driverName"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JDBC mysql = new JDBC();
        mysql.setDriverName(jdbc.getProperty("driverName"));
        mysql.setUrl(jdbc.getProperty("url"));
        mysql.setUser(jdbc.getProperty("user"));
        mysql.setPass(jdbc.getProperty("pass"));
        mysql.getConn();
        ResultSet rs = mysql.select(sql.getProperty("select_accountname") + "'" + user + "';");

        try {
            rs.last();
            if(rs.getRow() == 0) {
                uId =  0;//账号不存在
            }
            rs.beforeFirst();
            while(rs.next()){
                if(!rs.getObject("password").equals(pass)){
                    uId =  -1;//密码错误
                }else{
                    uId =  (int)rs.getObject("UID");//账号密码验证通过
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.close();
        return uId;
    }

    public static void main(String[] args){

        System.out.println(getUID("han", "83832391027a1f2f4d46ef882ff3a47d"));
    }

}
