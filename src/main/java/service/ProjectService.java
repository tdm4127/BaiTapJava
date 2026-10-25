// Service dự án: kiểm tra dữ liệu và gọi DAO.
package service;

import dao.ProjectDao;
import enums.ProjectResult;
import java.sql.Date;
import java.util.List;
import model.Project;

public class ProjectService {

    private ProjectDao prjDao = new ProjectDao();
    private static final int PAGE_SIZE = 10;

    public List<Project> getAllProjects() {
        return prjDao.findAll();
    }

    public ProjectResult addProject(Project prj) {
        if (prj.getProjectName() == null || prj.getProjectName().trim().isEmpty()) {
            return ProjectResult.EmptyName;
        } else if (prj.getStartDate() == null) {
            return ProjectResult.EmptyStartDate;
        } else if (prj.getEndDate() == null) {
            return ProjectResult.EmptyEndDate;
        } else {
            if (prjDao.insert(prj)) {
                return ProjectResult.Success;
            }
        }
        return ProjectResult.DatabaseError;
    }

    public ProjectResult updateProject(Project prj) {
        if (prj.getProjectName() == null || prj.getProjectName().trim().isEmpty()) {
            return ProjectResult.EmptyName;
        } else if (prj.getStartDate() == null) {
            return ProjectResult.EmptyStartDate;
        } else if (prj.getEndDate() == null) {
            return ProjectResult.EmptyEndDate;
        } else {
            if (prjDao.findById(prj.getProjectId()) == null) {
                return ProjectResult.NotFound;
            }
            if (prjDao.update(prj)) {
                return ProjectResult.Success;
            }
        }
        return ProjectResult.DatabaseError;
    }

    public ProjectResult deleteProject(int projectID) {
        if (prjDao.findById(projectID) == null) {
            return ProjectResult.NotFound;
        }
        
        if(prjDao.hasTasksByProjectId(projectID)){
            return ProjectResult.HasRelatedData;
        }
        
        if (prjDao.delete(projectID)) {
            return ProjectResult.Success;
        }
        return ProjectResult.DatabaseError;
    }

    public List<Project> findProject(String keyword, Date startDate, Date endDate, int page) {
        String projectName = null;
        Integer projectId = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            String kw = keyword.trim().toUpperCase();
            if ("PRJ".startsWith(kw) || kw.startsWith("PRJ")) {
                if (kw.length() > 3) {
                    String numberPart = kw.substring(3);
                    if (numberPart.matches("\\d{3}")) {
                        projectId = Integer.parseInt(numberPart);
                    }
                }
            } else {
                projectName = keyword.trim();
            }
        }

        int offset = (page - 1) * PAGE_SIZE;
        return prjDao.findProject(projectName, projectId, startDate, endDate, offset, PAGE_SIZE);
    }

    public int countProjects(String keyword, Date startDate, Date endDate) {
        String projectName = null;
        Integer projectId = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            String kw = keyword.trim().toUpperCase();
            if ("PRJ".startsWith(kw) || kw.startsWith("PRJ")) {
                if (kw.length() > 3) {
                    String numberPart = kw.substring(3);
                    if (numberPart.matches("\\d{3}")) {
                        projectId = Integer.parseInt(numberPart);
                    }
                }
            } else {
                projectName = keyword.trim();
            }
        }

        return prjDao.countProjects(projectName, projectId, startDate, endDate);
    }

    public int calculateTotalPages(int totalRecords) {
        return (int) Math.ceil((double) totalRecords / PAGE_SIZE);
    }

}
