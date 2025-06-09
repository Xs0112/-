package com.study.shop.dao;

import com.study.shop.po.Appraise;
import com.study.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppraiseDao {
    // 向t_appraise表中插入评价信息
    public int add(Appraise appraise) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_appraise(appraise, food_name, score, food_id) values(?,?,?,?)";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, appraise.getAppraise());
            pstmt.setString(2, appraise.getFood_name());
            pstmt.setInt(3, appraise.getScore());
            pstmt.setInt(4, appraise.getFood_id());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    // 根据餐品编号查询评价信息
    public List<Appraise> findByFoodId(int foodId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Appraise> list = new ArrayList<>();
        String sql = "select * from t_appraise where food_id =?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Appraise appraise = new Appraise();
                appraise.setId(rs.getInt("id"));
                appraise.setAppraise(rs.getString("appraise"));
                appraise.setFood_name(rs.getString("food_name"));
                appraise.setScore(rs.getInt("score"));
                appraise.setFood_id(rs.getInt("food_id"));
                list.add(appraise);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    // 查询所有评价信息
    public List<Appraise> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Appraise> list = new ArrayList<>();
        String sql = "select * from t_appraise";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Appraise appraise = new Appraise();
                appraise.setId(rs.getInt("id"));
                appraise.setAppraise(rs.getString("appraise"));
                appraise.setFood_name(rs.getString("food_name"));
                appraise.setScore(rs.getInt("score"));
                appraise.setFood_id(rs.getInt("food_id"));
                list.add(appraise);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }
}