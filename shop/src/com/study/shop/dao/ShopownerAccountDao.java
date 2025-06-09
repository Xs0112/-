package com.study.shop.dao;

import com.study.shop.po.Shopowner;
import com.study.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopownerAccountDao {

    /**
     * 添加店主账号
     * @param shopowner 店主对象
     * @return 影响的行数 1成功 0失败
     */
    public int add(Shopowner shopowner) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_shopowner(address, shopowner, phone, password) values(?,?,?,?)";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, shopowner.getAddress());
            pstmt.setString(2, shopowner.getShopowner());
            pstmt.setString(3, shopowner.getPhone());
            pstmt.setString(4, shopowner.getPassword());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    /**
     * 查询所有店主账号
     * @return 店主账号列表
     */
    public List<Shopowner> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Shopowner> list = new ArrayList<>();
        String sql = "select * from t_shopowner";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Shopowner shopowner = new Shopowner();
                shopowner.setShopId(rs.getInt("shop_id"));
                shopowner.setAddress(rs.getString("address"));
                shopowner.setShopowner(rs.getString("shopowner"));
                shopowner.setPhone(rs.getString("phone"));
                shopowner.setPassword(rs.getString("password"));
                list.add(shopowner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 根据主键查询店主账号
     * @param shopId 店主账号主键
     * @return 店主对象
     */
    public Shopowner findById(int shopId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Shopowner shopowner = null;
        String sql = "select * from t_shopowner where shop_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, shopId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                shopowner = new Shopowner();
                shopowner.setShopId(rs.getInt("shop_id"));
                shopowner.setAddress(rs.getString("address"));
                shopowner.setShopowner(rs.getString("shopowner"));
                shopowner.setPhone(rs.getString("phone"));
                shopowner.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return shopowner;
    }

    /**
     * 修改店主账号
     * @param shopowner 店主对象
     * @return 影响的行数 1成功 0失败
     */
    public int update(Shopowner shopowner) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_shopowner set address=?, shopowner=?, phone=?, password=? where shop_id =?";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, shopowner.getAddress());
            pstmt.setString(2, shopowner.getShopowner());
            pstmt.setString(3, shopowner.getPhone());
            pstmt.setString(4, shopowner.getPassword());
            pstmt.setInt(5, shopowner.getShopId());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    /**
     * 删除店主账号
     * @param shopId 店主账号编号
     * @return 影响的行数 1成功 0失败
     */
    public int delete(int shopId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from t_shopowner where shop_id = ?";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, shopId);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }
}