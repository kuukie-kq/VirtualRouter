package com.router.web.util;

import java.sql.*;

public class MysqlConnectUtils {
    public final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public final String DB = "jdbc:mysql://localhost/virtual_router_db";
    public final String USER = "root";
    public final String PASSWORD = "root";
    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    //建立MySQL连接
    public void mysqlInit() throws Exception {
        Class.forName(DRIVER);
        //System.out.println("Connecting to a selected database...");
        conn= DriverManager.getConnection(DB,USER,PASSWORD);
        stmt=conn.createStatement();
    }

    //关闭MySQL连接
    public void mysqlClose() throws SQLException {
        if(rs!=null)
            rs.close();
        if(stmt!=null)
            stmt.close();
        if(conn!=null)
            conn.close();
        //System.out.println("Closing the selected database...");
    }

    //添加
    public void mysqlUpdate(String sql) {
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询
    public void mysqlSelect(String sql) {
        try {
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //返回结果
    public ResultSet getRs() {
        return rs;
    }
}
