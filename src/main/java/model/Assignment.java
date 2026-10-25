// Model Assignment: chứa thông tin phân công.
package model;

import java.util.Date;

public class Assignment {
    private int assignmentId;
    private int taskId;
    private int employeeId;
    private Date assignedDate;
    private String taskName;
    private String employeeName;

    public Assignment(int taskId, int employeeId, Date assignedDate) {
        this.taskId = taskId;
        this.employeeId = employeeId;
        this.assignedDate = assignedDate;
    }

    public Assignment(int assignmentId, int taskId, int employeeId, Date assignedDate) {
        this.assignmentId = assignmentId;
        this.taskId = taskId;
        this.employeeId = employeeId;
        this.assignedDate = assignedDate;
    }

    public Assignment(int assignmentId, int taskId, int employeeId, Date assignedDate, String taskName, String employeeName) {
        this.assignmentId = assignmentId;
        this.taskId = taskId;
        this.employeeId = employeeId;
        this.assignedDate = assignedDate;
        this.taskName = taskName;
        this.employeeName = employeeName;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
