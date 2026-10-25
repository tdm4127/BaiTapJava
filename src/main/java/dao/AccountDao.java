// DAO tài khoản: đọc và lưu tài khoản trong SQL Server.
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;
import util.DBConnection;

public class AccountDao {

    public Account findAccount(String username) {
        String sql = """
        SELECT a.*, e.EmployeeName
        FROM Account a
        JOIN Employee e ON a.EmployeeId = e.EmployeeId
        WHERE a.Username = ?
        """;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String password = rs.getString("Password");
                    String role = rs.getString("Role");
                    int employeeId = rs.getInt("EmployeeId");
                    String employeeName = rs.getString("EmployeeName");

                    Account account = new Account(username, password, role, employeeId, employeeName);
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public boolean insert(Account account) {
        String sql = "INSERT INTO Account (Username, [Password], [Role], EmployeeId) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getRole());
            ps.setInt(4, account.getEmployeeId());

            int row = ps.executeUpdate();

            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }
}
