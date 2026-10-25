// Tạo kết nối đến cơ sở dữ liệu SQL Server.
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = 
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=WorkAssignmentManagement;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
        }
        return conn;
    }
}
