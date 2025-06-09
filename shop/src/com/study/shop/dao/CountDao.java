package com.study.shop.dao;

import com.study.shop.po.Count;
import com.study.shop.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountDao {

    // 查询销量统计数据（使用多表连接获取详细信息）
    public List<Count> findCountByQuantity() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Count> countList = new ArrayList<>();
        String sql = "SELECT f.food_id, f.food_name, SUM(o.amount) AS count, SUM(o.total) AS money, c.category_name " +
                "FROM t_order o " +
                "JOIN t_food f ON o.food_id = f.food_id " +
                "JOIN t_category c ON f.category_id = c.category_id " +
                "GROUP BY f.food_id, f.food_name, c.category_name " +
                "ORDER BY count DESC";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Count count = new Count();
                count.setFood_id(rs.getInt("food_id"));
                count.setFood_name(rs.getString("food_name"));
                count.setCount(rs.getInt("count"));
                count.setMoney(rs.getInt("money"));
                count.setCategory_name(rs.getString("category_name"));
                countList.add(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return countList;
    }

    // 查询销售额统计数据（使用多表连接获取详细信息）
    public List<Count> findCountBySales() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Count> countList = new ArrayList<>();
        String sql = "SELECT f.food_id, f.food_name, SUM(o.total) AS money, SUM(o.amount) AS count, c.category_name " +
                "FROM t_order o " +
                "JOIN t_food f ON o.food_id = f.food_id " +
                "JOIN t_category c ON f.category_id = c.category_id " +
                "GROUP BY f.food_id, f.food_name, c.category_name " +
                "ORDER BY money DESC";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Count count = new Count();
                count.setFood_id(rs.getInt("food_id"));
                count.setFood_name(rs.getString("food_name"));
                count.setMoney(rs.getInt("money"));
                count.setCount(rs.getInt("count"));
                count.setCategory_name(rs.getString("category_name"));
                countList.add(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return countList;
    }

    // 假设新增方法：根据类目统计销量（统计每个类目下所有食品的销量总和）
    public List<Count> countQuantityByCategory() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Count> countList = new ArrayList<>();
        String sql = "SELECT c.category_id, c.category_name, SUM(o.amount) AS count, SUM(o.total) AS money " +
                "FROM t_order o " +
                "JOIN t_food f ON o.food_id = f.food_id " +
                "JOIN t_category c ON f.category_id = c.category_id " +
                "GROUP BY c.category_id, c.category_name " +
                "ORDER BY count DESC";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Count count = new Count();
                count.setCategory_id(rs.getInt("category_id"));
                count.setCategory_name(rs.getString("category_name"));
                count.setMoney(rs.getInt("money"));
                count.setCount(rs.getInt("count"));
                countList.add(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return countList;
    }

    // 假设新增方法：根据类目统计销售额（统计每个类目下所有食品的销售额总和）
    public List<Count> countSalesByCategory() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Count> countList = new ArrayList<>();
        String sql = "SELECT c.category_id, c.category_name,SUM(o.total) AS money, SUM(o.amount) AS count " +
                "FROM t_order o " +
                "JOIN t_food f ON o.food_id = f.food_id " +
                "JOIN t_category c ON f.category_id = c.category_id " +
                "GROUP BY c.category_id, c.category_name " +
                "ORDER BY money DESC";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Count count = new Count();
                count.setCategory_id(rs.getInt("category_id"));
                count.setCategory_name(rs.getString("category_name"));
                count.setCount(rs.getInt("count"));
                count.setMoney(rs.getInt("money"));
                countList.add(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return countList;
    }
}