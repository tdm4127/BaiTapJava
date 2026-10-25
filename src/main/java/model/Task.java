// Model Task: chứa thông tin công việc.
package model;

import java.util.Date;

public class Task {

    private int taskId;
    private String taskName;
    private String description;
    private Date deadline;
    private String status;
    private String priority;
    private int projectId;
    private String projectName;

    public Task(String taskName, String description, Date deadline, String status, String priority, int projectId) {
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
    }

    public Task(int taskId, String taskName, String description, Date deadline, String status, String priority, int projectId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
    }

    public Task(int taskId, String taskName, String description, Date deadline, String status, String priority, int projectId, String projectName) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
}
