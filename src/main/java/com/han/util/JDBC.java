package com.han.util;

import java.sql.*;

/**
 *
 * @author hanminggui
 *
 */
public class JDBC {

    private Connection conn = null;
    private PreparedStatement statement = null;
    private String driverName = null;
    private String url = null;
    private String userName = null;
    private String passWord = null;

    public JDBC(){
    }
    public JDBC(String driverName, String url, String user, String pass){
        this.driverName = driverName;
        this.url = url;
        this.userName = user;
        this.passWord = pass;
    }

    /**
     * 设置连接信息
     * @param driverName
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setUser(String user) {
        this.userName = user;
    }
    public void setPass(String pass) {
        this.passWord = pass;
    }

    /**
     * 连接数据库
     * @return
     */
    public Connection getConn(){
        if (driverName==null || driverName.equals("") || url==null || url.equals("") || userName==null || passWord==null){
            System.err.println("数据库连接信息未设置完整！");
            System.err.println("请先设置数据库连接信息！！");
            return null;
        }
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userName, passWord);
        } catch (ClassNotFoundException e) {
            System.err.println("装载 JDBC/ODBC 驱动程序失败。" );
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println( "无法连接数据库" );
            e.printStackTrace();
        }
        return conn;
    }

    /**
     *断开数据库连接
     */
    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("关闭数据库问题 ：");
            e.printStackTrace();
        }
    }

    /**
     *执行数据库查询语句
     * @param sql SQL语句
     * @return
     */
    public ResultSet select(String sql) {
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 执行插入语句
     *
     * @param sql
     * @return
     */
    public boolean insertSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 执行删除语句
     * @param sql
     * @return
     */
    public boolean deleteSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 执行更新语句
     * @param sql
     * @return
     */
    public boolean updateSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }
    // 将返回结果按照一定格式显示
    void layoutStyle2(ResultSet rs) {
        System.out.println("-----------------");
        System.out.println("执行结果如下所示:");
        System.out.println("-----------------");
        System.out.println(" ID" + "/t/t" + "name" + "/t/t" + "说明");
        System.out.println("-----------------");
        try {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "/t/t"
                        + rs.getString("name") + "/t/t"
                        + rs.getString("function"));
            }
        } catch (SQLException e) {
            System.out.println("显示时数据库出错。");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("显示出错。");
            e.printStackTrace();
        }
    }

}
