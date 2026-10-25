// Service phân công: kiểm tra dữ liệu trước khi gọi DAO.
package service;

import dao.AssignmentDao;
import dao.EmployeeDao;
import dao.TaskDao;
import enums.AssignmentResult;
import java.util.List;
import model.Assignment;

public class AssignmentService {

    private AssignmentDao assignmentDao = new AssignmentDao();
    private EmployeeDao employeeDao = new EmployeeDao();
    private TaskDao taskDao = new TaskDao();
    private static final int PAGE_SIZE = 10;

    public AssignmentResult addAssignment(Assignment assignment) {
        if (assignment.getTaskId() <= 0) {
            return AssignmentResult.EmptyTask;
        } else if (assignment.getEmployeeId() <= 0) {
            return AssignmentResult.EmptyEmployee;
        } else if (assignment.getAssignedDate() == null) {
            return AssignmentResult.EmptyAssignedDate;
        } else {
            if (taskDao.findById(assignment.getTaskId()) == null) {
                return AssignmentResult.TaskNotFound;
            }
            if (employeeDao.findById(assignment.getEmployeeId()) == null) {
                return AssignmentResult.EmployeeNotFound;
            }
            if (assignmentDao.insert(assignment)) {
                return AssignmentResult.Success;
            }
        }
        return AssignmentResult.DatabaseError;
    }

    public AssignmentResult updateAssignment(Assignment assignment) {
        if (assignment.getTaskId() <= 0) {
            return AssignmentResult.EmptyTask;
        } else if (assignment.getEmployeeId() <= 0) {
            return AssignmentResult.EmptyEmployee;
        } else if (assignment.getAssignedDate() == null) {
            return AssignmentResult.EmptyAssignedDate;
        } else {
            if (assignmentDao.findById(assignment.getAssignmentId()) == null) {
                return AssignmentResult.AssignmentNotFound;
            }
            if (taskDao.findById(assignment.getTaskId()) == null) {
                return AssignmentResult.TaskNotFound;
            }
            if (employeeDao.findById(assignment.getEmployeeId()) == null) {
                return AssignmentResult.EmployeeNotFound;
            }
            if (assignmentDao.update(assignment)) {
                return AssignmentResult.Success;
            }
        }
        return AssignmentResult.DatabaseError;
    }

    public AssignmentResult deleteAssignment(int assignmentId) {
        if (assignmentDao.findById(assignmentId) == null) {
            return AssignmentResult.AssignmentNotFound;
        }

        if (assignmentDao.delete(assignmentId)) {
            return AssignmentResult.Success;
        }

        return AssignmentResult.DatabaseError;
    }

    public List<Assignment> findAssignment(String keyword, Integer employeeId, Integer taskId, int page) {
        Integer assignmentId = null;
        String searchName = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            String kw = keyword.trim().toUpperCase();

            if ("ASN".startsWith(kw) || kw.startsWith("ASN")) {
                if (kw.length() > 3) {
                    String numberPart = kw.substring(3);
                    if (numberPart.matches("\\d{3}")) {
                        assignmentId = Integer.parseInt(numberPart);
                    }
                }
            } else {
                searchName = keyword.trim();
            }
        }

        int offset = (page - 1) * PAGE_SIZE;
        return assignmentDao.findAssignment(searchName, assignmentId, employeeId, taskId, offset, PAGE_SIZE);
    }

    public int countAssignments(String keyword, Integer employeeId, Integer taskId) {
        Integer assignmentId = null;
        String searchName = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            String kw = keyword.trim().toUpperCase();

            if ("ASN".startsWith(kw) || kw.startsWith("ASN")) {
                if (kw.length() > 3) {
                    String numberPart = kw.substring(3);
                    if (numberPart.matches("\\d{3}")) {
                        assignmentId = Integer.parseInt(numberPart);
                    }
                }
            } else {
                searchName = keyword.trim();
            }
        }

        return assignmentDao.countAssignments(searchName, assignmentId, employeeId, taskId);
    }

    public int calculateTotalPages(int totalRecords) {
        return (int) Math.ceil((double) totalRecords / PAGE_SIZE);
    }

}
