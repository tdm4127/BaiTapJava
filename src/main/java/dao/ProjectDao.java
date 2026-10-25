// DAO dự án: đọc và ghi dữ liệu bảng Project.
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Project;
import util.DBConnection;

public class ProjectDao {

    public List<Project> findAll() {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT * FROM Project";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int projectId = rs.getInt("ProjectId");
                String projectName = rs.getString("ProjectName");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                String description = rs.getString("Description");

                Project prj = new Project(projectId, projectName, startDate, endDate, description);
                list.add(prj);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public Boolean insert(Project prj) {
        String sql = "INSERT INTO Project (ProjectName, StartDate, EndDate, Description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prj.getProjectName());
            ps.setDate(2, (Date) prj.getStartDate());
            ps.setDate(3, (Date) prj.getEndDate());
            ps.setString(4, prj.getDescription());

            int row = ps.executeUpdate();

            return row > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Boolean update(Project prj) {
        String sql = "UPDATE Project SET ProjectName = ?, StartDate = ?, EndDate = ?, Description = ? WHERE ProjectId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prj.getProjectName());
            ps.setDate(2, (Date) prj.getStartDate());
            ps.setDate(3, (Date) prj.getEndDate());
            ps.setString(4, prj.getDescription());
            ps.setInt(5, prj.getProjectId());

            int row = ps.executeUpdate();

            return row > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Boolean delete(int projectId) {
        String sql = "DELETE FROM Project WHERE ProjectId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, projectId);

            int row = ps.executeUpdate();

            return row > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Project findById(int projectId) {
        String sql = "SELECT * FROM Project WHERE ProjectId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, projectId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    String projectName = rs.getString("ProjectName");
                    Date startDate = rs.getDate("StartDate");
                    Date endDate = rs.getDate("EndDate");
                    String description = rs.getString("Description");

                    return new Project(projectId, projectName, startDate, endDate, description);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public boolean hasTasksByProjectId(int projectId) {
        String sql = "SELECT * FROM Task WHERE ProjectId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public List<Project> findProject(String projectName, Integer projectId, Date startDate, Date endDate,
            int offset, int pageSize) {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT * FROM Project WHERE 1 = 1";

        if (projectName != null && !projectName.trim().isEmpty()) {
            sql += " AND ProjectName LIKE ?";
        }
        if (projectId != null) {
            sql += " AND ProjectId = ?";
        }
        if (startDate != null) {
            sql += " AND StartDate >= ?";
        }
        if (endDate != null) {
            sql += " AND EndDate <= ?";
        }

        sql += " ORDER BY ProjectId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            if (projectName != null && !projectName.trim().isEmpty()) {
                ps.setString(index++, "%" + projectName + "%");
            }
            if (projectId != null) {
                ps.setInt(index++, projectId);
            }
            if (startDate != null) {
                ps.setDate(index++, startDate);
            }
            if (endDate != null) {
                ps.setDate(index++, endDate);
            }

            ps.setInt(index++, offset);
            ps.setInt(index, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Project(
                            rs.getInt("ProjectId"),
                            rs.getString("ProjectName"),
                            rs.getDate("StartDate"),
                            rs.getDate("EndDate"),
                            rs.getString("Description")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public int countProjects(String projectName, Integer projectId, Date startDate, Date endDate) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Project WHERE 1 = 1";

        if (projectName != null && !projectName.trim().isEmpty()) {
            sql += " AND ProjectName LIKE ?";
        }
        if (projectId != null) {
            sql += " AND ProjectId = ?";
        }
        if (startDate != null) {
            sql += " AND StartDate >= ?";
        }
        if (endDate != null) {
            sql += " AND EndDate <= ?";
        }

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            if (projectName != null && !projectName.trim().isEmpty()) {
                ps.setString(index++, "%" + projectName + "%");
            }
            if (projectId != null) {
                ps.setInt(index++, projectId);
            }
            if (startDate != null) {
                ps.setDate(index++, startDate);
            }
            if (endDate != null) {
                ps.setDate(index, endDate);
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
