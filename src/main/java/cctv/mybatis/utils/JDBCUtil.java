package cctv.mybatis.utils;

import java.sql.*;

/**
 * Created by CCTV-1 on 2017/5/12.
 */
public class JDBCUtil {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="JDBC:mysql://localhost:3306/201604ssm";
            String user="root";
            String password="123456";
            return DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeResource(Connection conn, PreparedStatement statement, ResultSet resultSet) {
        try {
            if(conn!=null){
                conn.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
