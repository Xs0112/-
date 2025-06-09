package com.study.shop.dao;

import com.study.shop.po.Admin;
import com.study.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    /**
     * 管理员登录验证
     * @param username 用户名
     * @param password 密码
     * @return 验证成功返回Admin对象，失败返回null
     */
    public Admin login(String username, String password) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM t_admin WHERE username = ? AND password = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setPhone(rs.getString("phone"));
                admin.setCreateTime(rs.getString("create_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pst, rs);
        }

        return admin;
    }

    /**
     * 添加管理员账号
     * @param admin 管理员对象
     * @return 影响的行数 1成功 0失败
     */
    public int add(Admin admin) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_admin(username, password, phone) values(?,?,?)";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getPhone());
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
     * 查询所有管理员账号
     * @return 管理员账号列表
     */
    public List<Admin> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Admin> list = new ArrayList<>();
        String sql = "select * from t_admin";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setPhone(rs.getString("phone"));
                admin.setCreateTime(rs.getString("create_time"));
                list.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 根据主键查询管理员账号
     * @param id 管理员账号主键
     * @return 管理员对象
     */
    public Admin findById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Admin admin = null;
        String sql = "select * from t_admin where id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setPhone(rs.getString("phone"));
                admin.setCreateTime(rs.getString("create_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn, pstmt, rs);
        }
        return admin;
    }

    /**
     * 修改管理员账号
     * @param admin 管理员对象
     * @return 影响的行数 1成功 0失败
     */
    public int update(Admin admin) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_admin set username=?, password=?, phone=?, create_time=? where id =?";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getPhone());
            pstmt.setString(4, admin.getCreateTime());
            pstmt.setInt(5, admin.getId());
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
     * 删除管理员账号
     * @param id 管理员账号编号
     * @return 影响的行数 1成功 0失败
     */
    public int delete(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from t_admin where id = ?";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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