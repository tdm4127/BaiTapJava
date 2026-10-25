// DAO phân công: thao tác bảng Assignment.
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Assignment;
import util.DBConnection;

public class AssignmentDao {

    public boolean insert(Assignment assignment) {
        String sql = "INSERT INTO Assignment (TaskId, EmployeeId, AssignedDate) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, assignment.getTaskId());
            ps.setInt(2, assignment.getEmployeeId());
            ps.setDate(3, new Date(assignment.getAssignedDate().getTime()));

            int row = ps.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    public boolean update(Assignment assignment) {
        String sql = "UPDATE Assignment SET TaskId = ?, EmployeeId = ?, AssignedDate = ? WHERE AssignmentId = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, assignment.getTaskId());
            ps.setInt(2, assignment.getEmployeeId());
            ps.setDate(3, new Date(assignment.getAssignedDate().getTime()));
            ps.setInt(4, assignment.getAssignmentId());

            int row = ps.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    public boolean delete(int assignmentId) {
        String sql = "DELETE FROM Assignment WHERE AssignmentId = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, assignmentId);

            int row = ps.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    public Assignment findById(int assignmentId) {
        String sql = "SELECT a.*, t.TaskName, e.EmployeeName "
                + "FROM Assignment a "
                + "JOIN Task t ON a.TaskId = t.TaskId "
                + "JOIN Employee e ON a.EmployeeId = e.EmployeeId "
                + "WHERE a.AssignmentId = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, assignmentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int taskId = rs.getInt("TaskId");
                    int employeeId = rs.getInt("EmployeeId");
                    Date assignedDate = rs.getDate("AssignedDate");
                    String taskName = rs.getString("TaskName");
                    String employeeName = rs.getString("EmployeeName");

                    return new Assignment(assignmentId, taskId, employeeId, assignedDate, taskName, employeeName);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public List<Assignment> findAssignment(String keyword, Integer assignmentId,
            Integer employeeId, Integer taskId, int offset, int pageSize) {

        List<Assignment> list = new ArrayList<>();

        String sql = "SELECT a.*, t.TaskName, e.EmployeeName "
                + "FROM Assignment a "
                + "JOIN Task t ON a.TaskId = t.TaskId "
                + "JOIN Employee e ON a.EmployeeId = e.EmployeeId "
                + "WHERE 1 = 1";

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += " AND (t.TaskName LIKE ? OR e.EmployeeName LIKE ?)";
        }
        if (assignmentId != null) {
            sql += " AND a.AssignmentId = ?";
        }
        if (employeeId != null) {
            sql += " AND a.EmployeeId = ?";
        }
        if (taskId != null) {
            sql += " AND a.TaskId = ?";
        }

        sql += " ORDER BY a.AssignmentId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
                ps.setString(index++, "%" + keyword + "%");
            }
            if (assignmentId != null) {
                ps.setInt(index++, assignmentId);
            }
            if (employeeId != null) {
                ps.setInt(index++, employeeId);
            }
            if (taskId != null) {
                ps.setInt(index++, taskId);
            }

            ps.setInt(index++, offset);
            ps.setInt(index, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Assignment(
                            rs.getInt("AssignmentId"),
                            rs.getInt("TaskId"),
                            rs.getInt("EmployeeId"),
                            rs.getDate("AssignedDate"),
                            rs.getString("TaskName"),
                            rs.getString("EmployeeName")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    public int countAssignments(String keyword, Integer assignmentId, Integer employeeId, Integer taskId) {
        int count = 0;

        String sql = "SELECT COUNT(*) "
                + "FROM Assignment a "
                + "JOIN Task t ON a.TaskId = t.TaskId "
                + "JOIN Employee e ON a.EmployeeId = e.EmployeeId "
                + "WHERE 1 = 1";

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += " AND (t.TaskName LIKE ? OR e.EmployeeName LIKE ?)";
        }
        if (assignmentId != null) {
            sql += " AND a.AssignmentId = ?";
        }
        if (employeeId != null) {
            sql += " AND a.EmployeeId = ?";
        }
        if (taskId != null) {
            sql += " AND a.TaskId = ?";
        }

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
                ps.setString(index++, "%" + keyword + "%");
            }
            if (assignmentId != null) {
                ps.setInt(index++, assignmentId);
            }
            if (employeeId != null) {
                ps.setInt(index++, employeeId);
            }
            if (taskId != null) {
                ps.setInt(index, taskId);
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

    public List<Assignment> findByEmployeeId(int employeeId) {
        List<Assignment> list = new ArrayList<>();
        String sql = "SELECT * FROM Assignment WHERE EmployeeId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int assignmentId = rs.getInt("AssignmentId");
                    int taskId = rs.getInt("TaskId");
                    employeeId = rs.getInt("EmployeeId");
                    Date assignedDate = rs.getDate("AssignedDate");

                    Assignment asn =  new Assignment(assignmentId, taskId, employeeId, assignedDate);
                    list.add(asn);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }
}
