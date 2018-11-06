package com.example.demo.controller;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/nwork";
        String name = "root";
        String password = "5641";
        Connection conn = DriverManager.getConnection(url,name,password);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from nc_shop");
        while (rs.next()){
            System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
             + rs.getObject(3)+"\t" + rs.getObject(4)+"\t" + rs.getObject(5));
        }
        rs.close();
        statement.close();
        conn.close();
    }

    /*private static String url = "jdbc:mysql://localhost:3306/nwork";
    private static String name = "root";
    private static String password = "5641";

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,name,password);
    }*/



}
