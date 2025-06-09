package com.study.shop.dao;

import com.study.shop.po.Food;
import com.study.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    /**
     * 增加餐品
     * @param food 餐品对象
     * @return 影响的行数 1成功 0失败
     */
    public int add(Food food){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_food(food_name,category_id,price,description) values(?,?,?,?)";
        int result = 0;
        try {
            conn= JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,food.getFoodName());
            pstmt.setInt(2,food.getCategoryId());
            pstmt.setInt(3,food.getPrice());
            pstmt.setString(4,food.getDescription());
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
     * 查询所有餐品
     * @return
     */
    public List<Food> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String sql = "select t1.*,category_name from " +
                "t_food t1,t_category t2 " +
                "where t1.category_id = t2.category_id " +
                "and t1.is_deleted = 0";
        // CTRL + ALT +T
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Food food = new Food();
                food.setFoodId(rs.getInt(1));
                food.setFoodName(rs.getString(2));
                food.setCategoryId(rs.getInt(3));
                food.setPrice(rs.getInt(4));
                food.setDescription(rs.getString(5));
                food.setDeleted(rs.getBoolean(6));
                food.setCategoryName(rs.getString(7));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     *  根据关键字查询餐品
     * @param key 关键字
     * @return
     */
    public List<Food> findByKey(String key) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String sql = "select t1.*,category_name from " +
                "t_food t1,t_category t2 " +
                "where t1.category_id = t2.category_id " +
                "and t1.is_deleted = 0 and concat(food_name,category_name) like ?";;
        // CTRL + ALT +T
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+key+"%");
            rs = pstmt.executeQuery();
            while(rs.next()){
                Food food = new Food();
                food.setFoodId(rs.getInt(1));
                food.setFoodName(rs.getString(2));
                food.setCategoryId(rs.getInt(3));
                food.setPrice(rs.getInt(4));
                food.setDescription(rs.getString(5));
                food.setDeleted(rs.getBoolean(6));
                food.setCategoryName(rs.getString(7));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     * 根据主键查询餐品
     * @param foodId 餐品主键
     * @return 餐品对象
     */
    public Food findById(int foodId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Food food = null;
        String sql = "select t1.*,category_name from " +
                "t_food t1,t_category t2 " +
                "where t1.category_id = t2.category_id " +
                "and t1.is_deleted = 0 and food_id = ?";;
        // CTRL + ALT +T
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,foodId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                food = new Food();
                food.setFoodId(rs.getInt(1));
                food.setFoodName(rs.getString(2));
                food.setCategoryId(rs.getInt(3));
                food.setPrice(rs.getInt(4));
                food.setDescription(rs.getString(5));
                food.setDeleted(rs.getBoolean(6));
                food.setCategoryName(rs.getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return food;
    }

    /**
     * 修改餐品
     * @param food 餐品对象
     * @return
     */
    public int update(Food food){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_food set food_name=?,category_id=?,price=?,description=? where food_id =?";
        int result = 0;
        try {
            conn= JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,food.getFoodName());
            pstmt.setInt(2,food.getCategoryId());
            pstmt.setInt(3,food.getPrice());
            pstmt.setString(4,food.getDescription());
            pstmt.setInt(5,food.getFoodId());
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
     * 删除餐品
     * @param foodId 餐品编号
     * @return
     */
    public int delete(int foodId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_food set is_deleted =1 where food_id = ?";
        int result = 0;
        try {
            conn= JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,foodId);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }
    // 检查餐品名称是否已存在
    public boolean existsByName(String foodName) {
        String sql = "SELECT COUNT(*) FROM t_food WHERE food_name = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, foodName);
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