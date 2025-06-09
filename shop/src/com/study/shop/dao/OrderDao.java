package com.study.shop.dao;

import com.study.shop.po.Order;
import com.study.shop.util.DateUtil;
import com.study.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    /**
     * 添加订单，一搬是多个餐品，用集合做参数
     * @param list 餐品集合
     * @return
     */
    public boolean  addOrder(List<Order> list){
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;
        String sql = "insert into t_order(desk_number,phone,food_id,price,amount,total, address,order_type) values(?,?,?,?,?,?,?,?)";// 主键自动增长
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (Order order : list) {
                // 遍历批量增加的集合
                pstmt.setInt(1, order.getDeskNumber());
                pstmt.setString(2, order.getPhone());
                pstmt.setDouble(3, order.getFoodId());
                pstmt.setInt(4, order.getPrice());
                pstmt.setInt(5,order.getAmount());
                pstmt.setInt(6,order.getTotal());
                pstmt.setString(7, order.getAddress());
                pstmt.setInt(8, order.getOrderType());

                //将参数添加到批量处理
                pstmt.addBatch();
            }
            //执行批量处理
            int[] rows = pstmt.executeBatch();
            //System.out.println(Arrays.toString(rows));
            if (rows.length > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }

    /**
     * 查询订单
     * @param phone 顾客手机号
     * @param option 选项 1当天未处理 2当天已处理 3所有
     * @return
     */
    public List<Order> findRecord(String phone,int option){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        List<Order> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();

            if (option == 1) {
                String today = DateUtil.getCurrentDate();
                sql = "select t1.*,food_name from t_order t1,t_food t2 " +
                        " where t1.food_id = t2.food_id and " +
                        "phone=? and DATE(create_time)=? and state=1";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, phone);
                pstmt.setString(2, today);
            }
            if (option == 2) {
                String today = DateUtil.getCurrentDate();
                sql = "select t1.*,food_name from t_order t1,t_food t2 " +
                        " where t1.food_id = t2.food_id and " +
                        "phone=? and DATE(create_time)=? and state=2";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, phone);
                pstmt.setString(2, today);
            }
            if (option == 3) {
                sql = "select t1.*,food_name from t_order t1,t_food t2 " +
                        " where t1.food_id = t2.food_id and " +
                        "phone=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, phone);
            }

            // 执行
            rs = pstmt.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt(1));
                order.setDeskNumber(rs.getInt(2));
                order.setPhone(rs.getString(3));
                order.setFoodId(rs.getInt(4));
                order.setPrice(rs.getInt(5));
                order.setAmount(rs.getInt(6));
                order.setTotal(rs.getInt(7));
                // 获取日期时间
                order.setCreateTime(rs.getTimestamp(8));
                order.setState(rs.getInt(9));
                order.setFoodName(rs.getString("food_name"));
                order.setOrderType(rs.getInt("order_type")); // 新增
                order.setAddress(rs.getString("address"));

                // 添加到集合
                list.add(order);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     * 查询未处理的订单
     * @return
     */
    public List<Order> findNoHandleRecord(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        List<Order> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            sql = "select t1.*,food_name from t_order t1,t_food t2 " +
                    " where t1.food_id = t2.food_id and state=1";
            pstmt = conn.prepareStatement(sql);
            // 执行
            rs = pstmt.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt(1));
                order.setDeskNumber(rs.getInt(2));
                order.setPhone(rs.getString(3));
                order.setFoodId(rs.getInt(4));
                order.setPrice(rs.getInt(5));
                order.setAmount(rs.getInt(6));
                order.setTotal(rs.getInt(7));
                // 获取日期时间
                order.setCreateTime(rs.getTimestamp(8));
                order.setState(rs.getInt(9));
                order.setFoodName(rs.getString("food_name"));
                order.setOrderType(rs.getInt("order_type")); // 新增
                order.setAddress(rs.getString("address"));
                // 添加到集合
                list.add(order);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeAll(conn,pstmt,rs);
        }
        return list;
    }

    /**
     * 全部接单
     * @return
     */
    public int receiveAll(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_order set state=2 where state=1";
        int result = 0;
        try{
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }

    /**
     * 部分接单
     * @param oids
     * @return
     */
    public int receivePart(String oids){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_order set state=2 where state=1 and order_id in ("+oids+")";
        int result = 0;
        try{
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }

    /**
     * 取消接单
     * @param oids
     * @return
     */
    public int cancleOrder(String oids){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_order set state=3 where state=1 and order_id in ("+oids+")";
        int result = 0;
        try{
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeAll(conn,pstmt,null);
        }
        return result;
    }

}