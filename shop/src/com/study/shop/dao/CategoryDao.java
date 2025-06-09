package com.study.shop.dao;

import com.study.shop.po.Category;
import com.study.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    /**
     * 增加类目
     * @param category 类目对象
     * @return 影响的行数 1 成功 0失败
     */
    public int add(Category category){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_category(category_name) values(?)";
        int result = 0;
        try {
            conn= JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,category.getCategoryName());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }

    /**
     * 查询所有类目
     * @return
     */
    public List<Category> findAll(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<>();
        String sql = "select * from t_category where is_deleted = 0";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt(1));
                category.setCategoryName(rs.getString(2));
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     * 根据关键字查询
     * @param key
     * @return
     */
    public List<Category> findByKey(String key){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<>();
        String sql = "select * from t_category where is_deleted = 0 and category_name like ?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            // 设置关键字参数
            pstmt.setString(1,"%"+key+"%");
            rs = pstmt.executeQuery();
            while(rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt(1));
                category.setCategoryName(rs.getString(2));
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     * 根据主键查找对象
     * @param categoryId
     * @return
     */
    public Category findById(int categoryId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Category category = null;
        String sql = "select * from t_category where is_deleted = 0 and category_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            // 设置关键字参数
            pstmt.setInt(1,categoryId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                category = new Category();
                category.setCategoryId(rs.getInt(1));
                category.setCategoryName(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return category;
    }

    /**
     * 修改类目
     * @param category 类目对象
     * @return 影响的行数 1 成功 0 失败
     */
    public int update(Category category){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_category set category_name=? where category_id=?";
        int result = 0;
        try {
            conn= JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,category.getCategoryName());
            pstmt.setInt(2,category.getCategoryId());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }

    /**
     * 根据主键删除信息
     * @param categoryId
     * @return
     */
    public int delete(int categoryId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        // 逻辑删除
        String sql = "update t_category set is_deleted = 1 where category_id = ?";
        int result = 0;
        try {
            conn= JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,categoryId);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }
    // 检查类目名称是否已存在
    public boolean existsByName(String categoryName) {
        String sql = "SELECT COUNT(*) FROM t_category WHERE category_name = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
