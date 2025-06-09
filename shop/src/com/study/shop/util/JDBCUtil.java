package com.study.shop.util;

import java.sql.*;

/**
 * 链接数据库的工具类
 */
public class JDBCUtil {
    //声明连接数据库使用的对象
    //连接数据库驱动
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库链接地址
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_shop?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
    //数据库链接用户名df s
    private static final String USER = "root";
    //自己的数据库密码
    private static final String PASSWORD = "123456";

    //注册加载驱动
    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
             conn=DriverManager.getConnection(URL, USER,PASSWORD);
             return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭资源
    public static void closeAll(Connection con, PreparedStatement pst, ResultSet res) {
        try {
            if(con != null) {
                con.close();
            }
            if(pst != null) {
                pst.close();
            }
            if(res != null) {
                res.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
