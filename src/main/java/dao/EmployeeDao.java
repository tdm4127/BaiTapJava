// DAO nhân viên: đọc và ghi dữ liệu bảng Employee.
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import util.DBConnection;

public class EmployeeDao {

    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int employeeId = rs.getInt("EmployeeId");
                String employeeName = rs.getString("EmployeeName");
                String position = rs.getString("Position");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("PhoneNumber");

                Employee emp = new Employee(employeeId, employeeName, position, email, phoneNumber);
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public int insert(Employee emp) {
        String sql = "INSERT INTO Employee (EmployeeName, Position, Email, PhoneNumber) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, emp.getEmployeeName());
            ps.setString(2, emp.getPosition());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhoneNumber());

            int row = ps.executeUpdate();

            if (row > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

        return -1;
    }

    public Employee findByEmail(String email) {
        String sql = "SELECT * FORM Employee WHERE Email = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int employeeId = rs.getInt("EmployeeID");
                    String employeeName = rs.getString("EmployeeName");
                    String position = rs.getString("Position");
                    String phoneNumber = rs.getString("PhoneNumber");

                    Employee emp = new Employee(employeeId, employeeName, position, email, phoneNumber);
                    return emp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public Employee findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FORM Employee WHERE PhoneNumber = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int employeeId = rs.getInt("EmployeeID");
                String employeeName = rs.getString("EmployeeName");
                String position = rs.getString("Position");
                String email = rs.getString("Email");

                Employee emp = new Employee(employeeId, employeeName, position, email, phoneNumber);
                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public boolean update(Employee emp) {
        String sql = "UPDATE Employee "
                + "SET EmployeeName = ?, Position = ?, Email = ?, PhoneNumber = ? "
                + "WHERE EmployeeId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, emp.getEmployeeName());
            ps.setString(2, emp.getPosition());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhoneNumber());
            ps.setInt(5, emp.getEmployeeId());

            int row = ps.executeUpdate();

            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Employee findByEmailExceptEmployeeId(String email, int employeeId) {
        String sql = "SELECT * FROM Employee WHERE Email = ? AND EmployeeId != ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, email);
            ps.setInt(2, employeeId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String employeeName = rs.getString("EmployeeName");
                    String position = rs.getString("Position");
                    String phoneNumber = rs.getString("PhoneNumber");

                    return new Employee(employeeId, employeeName, position, email, phoneNumber);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public Employee findByPhoneNumberExceptEmployeeId(String phoneNumber, int employeeId) {
        String sql = "SELECT * FROM Employee WHERE PhoneNumber = ? AND EmployeeId != ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, phoneNumber);
            ps.setInt(2, employeeId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String employeeName = rs.getString("EmployeeName");
                    String position = rs.getString("Position");
                    String email = rs.getString("Email");

                    return new Employee(employeeId, employeeName, position, email, phoneNumber);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(int employeeId) {
        String sql = "DELETE FROM Employee WHERE EmployeeId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, employeeId);

            int row = ps.executeUpdate();

            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Employee findById(int employeeId) {
        String sql = "SELECT * FROM Employee WHERE EmployeeId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String employeeName = rs.getString("EmployeeName");
                    String position = rs.getString("Position");
                    String email = rs.getString("Email");
                    String phoneNumber = rs.getString("PhoneNumber");
                    return new Employee(employeeId, employeeName, position, email, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public boolean hasAssignmentsByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM Assignment WHERE EmployeeId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public List<Employee> findEmployee(String employeeName, Integer employeeId, String position, int offset, int pageSize) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE 1 = 1";

        if (employeeName != null && !employeeName.trim().isEmpty()) {
            sql += " AND EmployeeName LIKE ?";
        }
        if (employeeId != null) {
            sql += " AND EmployeeId = ?";
        }
        if (position != null && !position.trim().isEmpty()) {
            sql += " AND Position = ?";
        }

        sql += " ORDER BY EmployeeId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            if (employeeName != null && !employeeName.trim().isEmpty()) {
                ps.setString(index++, "%" + employeeName + "%");
            }
            if (employeeId != null) {
                ps.setInt(index++, employeeId);
            }
            if (position != null && !position.trim().isEmpty()) {
                ps.setString(index++, position);
            }

            ps.setInt(index++, offset);
            ps.setInt(index, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("EmployeeId");
                    String name = rs.getString("EmployeeName");
                    String empPosition = rs.getString("Position");
                    String email = rs.getString("Email");
                    String phoneNumber = rs.getString("PhoneNumber");

                    Employee emp = new Employee(id, name, empPosition, email, phoneNumber);
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public int countEmployees(String employeeName, Integer employeeId, String position) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Employee WHERE 1 = 1";

        if (employeeName != null && !employeeName.trim().isEmpty()) {
            sql += " AND EmployeeName LIKE ?";
        }
        if (employeeId != null) {
            sql += " AND EmployeeId = ?";
        }
        if (position != null && !position.trim().isEmpty()) {
            sql += " AND Position = ?";
        }

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            if (employeeName != null && !employeeName.trim().isEmpty()) {
                ps.setString(index++, "%" + employeeName + "%");
            }
            if (employeeId != null) {
                ps.setInt(index++, employeeId);
            }
            if (position != null && !position.trim().isEmpty()) {
                ps.setString(index, position);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return count;
    }

}
