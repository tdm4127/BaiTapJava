// Model Project: chứa thông tin dự án.
package model;

import java.util.Date;

public class Project {
    private int projectId;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private String description;

    public Project(String projectName, Date startDate, Date endDate, String description) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Project(int projectId, String projectName, Date startDate, Date endDate, String description) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    } 
}
