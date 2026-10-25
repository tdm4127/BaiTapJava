// Service công việc: kiểm tra dữ liệu và gọi DAO.
package service;

import dao.ProjectDao;
import dao.TaskDao;
import enums.TaskResult;
import java.util.List;
import model.Task;

public class TaskService {

    private TaskDao taskDao = new TaskDao();
    private ProjectDao prjDao = new ProjectDao();
    private static final int PAGE_SIZE = 10;

    public List<Task> getAllTasks() {
        return taskDao.findAll();
    }

    public TaskResult addTask(Task task) {
        if (task.getTaskName() == null || task.getTaskName().trim().isEmpty()) {
            return TaskResult.EmptyTask;
        } else if (task.getDeadline() == null) {
            return TaskResult.EmptyDeadline;
        } else if (task.getStatus() == null || task.getStatus().trim().isEmpty()) {
            return TaskResult.EmptyStatus;
        } else if (task.getPriority() == null || task.getPriority().trim().isEmpty()) {
            return TaskResult.EmptyPriority;
        } else {
            if (prjDao.findById(task.getProjectId()) == null) {
                return TaskResult.ProjectNotFound;
            }
            if (taskDao.insert(task)) {
                return TaskResult.Success;
            }
        }
        return TaskResult.DatabaseError;
    }

    public TaskResult updateTask(Task task) {
        if (task.getTaskName() == null || task.getTaskName().trim().isEmpty()) {
            return TaskResult.EmptyTask;
        } else if (task.getDeadline() == null) {
            return TaskResult.EmptyDeadline;
        } else if (task.getStatus() == null || task.getStatus().trim().isEmpty()) {
            return TaskResult.EmptyStatus;
        } else if (task.getPriority() == null || task.getPriority().trim().isEmpty()) {
            return TaskResult.EmptyPriority;
        } else {
            if (taskDao.findById(task.getTaskId()) == null) {
                return TaskResult.TaskNotFound;
            }
            if (prjDao.findById(task.getProjectId()) == null) {
                return TaskResult.ProjectNotFound;
            }
            if (taskDao.update(task)) {
                return TaskResult.Success;
            }
        }
        return TaskResult.DatabaseError;
    }

    public TaskResult deleteTask(int taskId) {
        if (taskDao.findById(taskId) == null) {
            return TaskResult.TaskNotFound;
        }
        if (taskDao.delete(taskId)) {
            return TaskResult.Success;
        }
        return TaskResult.DatabaseError;
    }

    public List<Task> findTask(String keyword, String priority, String status, Integer projectId, int page) {
        String taskName = null;
        Integer taskId = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            String kw = keyword.trim().toUpperCase();
            if ("TSK".startsWith(kw) || kw.startsWith("TSK")) {
                if (kw.length() > 3) {
                    String numberPart = kw.substring(3);
                    if (numberPart.matches("\\d{3}")) {
                        taskId = Integer.parseInt(numberPart);
                    }
                }
            } else {
                taskName = keyword.trim();
            }
        }

        int offset = (page - 1) * PAGE_SIZE;
        return taskDao.findTask(taskName, taskId, priority, status, projectId, offset, PAGE_SIZE);
    }

    public int countTasks(String keyword, String priority, String status, Integer projectId) {
        String taskName = null;
        Integer taskId = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            String kw = keyword.trim().toUpperCase();
            if ("TSK".startsWith(kw) || kw.startsWith("TSK")) {
                if (kw.length() > 3) {
                    String numberPart = kw.substring(3);
                    if (numberPart.matches("\\d{3}")) {
                        taskId = Integer.parseInt(numberPart);
                    }
                }
            } else {
                taskName = keyword.trim();
            }
        }

        return taskDao.countTasks(taskName, taskId, priority, status, projectId);
    }

    public int calculateTotalPages(int totalRecords) {
        return (int) Math.ceil((double) totalRecords / PAGE_SIZE);
    }

}
