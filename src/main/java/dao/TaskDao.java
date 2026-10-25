// DAO công việc: đọc và ghi dữ liệu bảng Task.
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Task;
import util.DBConnection;

public class TaskDao {

    public List<Task> findAll() {
        List<Task> list = new ArrayList<>();

        String sql = "SELECT t.*, p.ProjectName "
                + "FROM Task t "
                + "JOIN Project p ON t.ProjectId = p.ProjectId";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int taskId = rs.getInt("TaskId");
                String taskName = rs.getString("TaskName");
                String description = rs.getString("Description");
                Date deadline = rs.getDate("Deadline");
                String status = rs.getString("Status");
                String priority = rs.getString("Priority");
                int projectId = rs.getInt("ProjectId");
                String projectName = rs.getString("ProjectName");

                Task task = new Task(taskId, taskName, description, deadline, status, priority, projectId, projectName);
                list.add(task);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    // Lấy các công việc được phân công cho một nhân viên.
    public List<Task> findByEmployeeId(int employeeId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT t.*, p.ProjectName "
                + "FROM Assignment a "
                + "JOIN Task t ON a.TaskId = t.TaskId "
                + "JOIN Project p ON t.ProjectId = p.ProjectId "
                + "WHERE a.EmployeeId = ? "
                + "ORDER BY t.TaskId";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task(
                            rs.getInt("TaskId"),
                            rs.getString("TaskName"),
                            rs.getString("Description"),
                            rs.getDate("Deadline"),
                            rs.getString("Status"),
                            rs.getString("Priority"),
                            rs.getInt("ProjectId"),
                            rs.getString("ProjectName")
                    );
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

        return tasks;
    }


    // Đổi trạng thái Task khi nhân viên nhấn Bắt đầu hoặc Hoàn thành.
    public boolean updateMyTaskStatus(int taskId, int employeeId, String action) {
        String newStatus;
        String statusVi;
        String statusEn;

        // Chỉ cho phép chuyển sang trạng thái tiếp theo.
        if ("start".equals(action)) {
            newStatus = "Đang thực hiện";
            statusVi = "Chưa bắt đầu";
            statusEn = "Pending";
        } else if ("finish".equals(action)) {
            newStatus = "Hoàn thành";
            statusVi = "Đang thực hiện";
            statusEn = "In Progress";
        } else {
            return false;
        }

        // JOIN Assignment để chỉ cập nhật công việc được giao cho nhân viên này.
        String sql = "UPDATE t SET [Status] = ? "
                + "FROM Task t "
                + "JOIN Assignment a ON a.TaskId = t.TaskId "
                + "WHERE t.TaskId = ? AND a.EmployeeId = ? "
                + "AND t.[Status] IN (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, taskId);
            ps.setInt(3, employeeId);
            ps.setString(4, statusVi);
            ps.setString(5, statusEn);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean insert(Task task) {
        String sql = "INSERT INTO Task (TaskName, [Description], Deadline, [Status], [Priority], ProjectId) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTaskName());
            ps.setString(2, task.getDescription());
            ps.setDate(3, (Date) task.getDeadline());
            ps.setString(4, task.getStatus());
            ps.setString(5, task.getPriority());
            ps.setInt(6, task.getProjectId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Boolean update(Task task) {
        String sql = "UPDATE Task SET TaskName = ?, [Description] = ?, Deadline = ?, "
                + "[Status] = ?, [Priority] = ?, ProjectId = ? "
                + "WHERE TaskId = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTaskName());
            ps.setString(2, task.getDescription());
            ps.setDate(3, (Date) task.getDeadline());
            ps.setString(4, task.getStatus());
            ps.setString(5, task.getPriority());
            ps.setInt(6, task.getProjectId());
            ps.setInt(7, task.getTaskId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Boolean delete(int taskId) {
        String sql = "DELETE FROM Task WHERE TaskId = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taskId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Task findById(int taskId) {
        String sql = "SELECT t.*, p.ProjectName "
                + "FROM Task t "
                + "JOIN Project p ON t.ProjectId = p.ProjectId "
                + "WHERE t.TaskId = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, taskId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Task(
                            rs.getInt("TaskId"),
                            rs.getString("TaskName"),
                            rs.getString("Description"),
                            rs.getDate("Deadline"),
                            rs.getString("Status"),
                            rs.getString("Priority"),
                            rs.getInt("ProjectId"),
                            rs.getString("ProjectName")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public List<Task> findTask(String taskName, Integer taskId, String priority, String status,
            Integer projectId, int offset, int pageSize) {
        List<Task> tasks = new ArrayList<>();

        String sql = "SELECT t.*, p.ProjectName "
                + "FROM Task t "
                + "JOIN Project p ON t.ProjectId = p.ProjectId "
                + "WHERE 1 = 1";

        if (taskName != null && !taskName.trim().isEmpty()) {
            sql += " AND t.TaskName LIKE ?";
        }
        if (taskId != null) {
            sql += " AND t.TaskId = ?";
        }
        if (priority != null && !priority.trim().isEmpty()) {
            sql += " AND t.Priority = ?";
        }
        if (status != null && !status.trim().isEmpty()) {
            sql += " AND t.Status = ?";
        }
        if (projectId != null) {
            sql += " AND t.ProjectId = ?";
        }

        sql += " ORDER BY t.TaskId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            if (taskName != null && !taskName.trim().isEmpty()) {
                ps.setString(index++, "%" + taskName + "%");
            }
            if (taskId != null) {
                ps.setInt(index++, taskId);
            }
            if (priority != null && !priority.trim().isEmpty()) {
                ps.setString(index++, priority);
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setString(index++, status);
            }
            if (projectId != null) {
                ps.setInt(index++, projectId);
            }

            ps.setInt(index++, offset);
            ps.setInt(index, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tasks.add(new Task(
                            rs.getInt("TaskId"),
                            rs.getString("TaskName"),
                            rs.getString("Description"),
                            rs.getDate("Deadline"),
                            rs.getString("Status"),
                            rs.getString("Priority"),
                            rs.getInt("ProjectId"),
                            rs.getString("ProjectName")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return tasks;
    }

    public int countTasks(String taskName, Integer taskId, String priority, String status, Integer projectId) {
        int count = 0;

        String sql = "SELECT COUNT(*) "
                + "FROM Task t "
                + "WHERE 1 = 1";

        if (taskName != null && !taskName.trim().isEmpty()) {
            sql += " AND t.TaskName LIKE ?";
        }
        if (taskId != null) {
            sql += " AND t.TaskId = ?";
        }
        if (priority != null && !priority.trim().isEmpty()) {
            sql += " AND t.Priority = ?";
        }
        if (status != null && !status.trim().isEmpty()) {
            sql += " AND t.Status = ?";
        }
        if (projectId != null) {
            sql += " AND t.ProjectId = ?";
        }

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            if (taskName != null && !taskName.trim().isEmpty()) {
                ps.setString(index++, "%" + taskName + "%");
            }
            if (taskId != null) {
                ps.setInt(index++, taskId);
            }
            if (priority != null && !priority.trim().isEmpty()) {
                ps.setString(index++, priority);
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setString(index++, status);
            }
            if (projectId != null) {
                ps.setInt(index, projectId);
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
