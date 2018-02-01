package cctv.mybatis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by CCTV-1 on 2017/5/12.
 */
public class JDBCUtil {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            String url=properties.getProperty("url");
            String user=properties.getProperty("root");
            String password = properties.getProperty("password");
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
