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

    public static void yanzheng(String user){
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
        ResultSet rs = mysql.select(sql.getProperty("select_uid") + "'" + user + "';");
        try {
            while(rs.next()){
                System.out.println(rs.getObject("Host"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.close();
    }

    public static void main(String[] args){
        yanzheng("root");
    }

}
