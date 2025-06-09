package com.study.shop.dao;

import com.study.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShopownerDao {
    /**
     * 店主登录
     * @param phone 电话
     * @param password 密码
     * @return 成功 true 失败 false
     */
    public boolean login(String phone,String password){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from t_shopowner where phone=? and password = ?";
        boolean result = false;
        try {
            conn= JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,phone);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            if(rs.next()){
                // 如果查询到数据
                result =  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }
}
