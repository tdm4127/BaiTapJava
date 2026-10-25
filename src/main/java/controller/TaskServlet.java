// Servlet công việc: nhận yêu cầu và gọi Service.
package controller;

import enums.TaskResult;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.Task;
import service.ProjectService;
import service.TaskService;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {

    private TaskService taskService = new TaskService();
    private ProjectService projectService = new ProjectService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String keyword = request.getParameter("keyword");
        String priority = request.getParameter("priority");
        String status = request.getParameter("status");
        String projectIdParam = request.getParameter("projectId");
        String pageParam = request.getParameter("page");

        Integer projectId = null;

        if (projectIdParam != null && !projectIdParam.trim().isEmpty()) {
            projectId = Integer.valueOf(projectIdParam);
        }

        int currentPage = pageParam == null ? 1 : Integer.parseInt(pageParam);

        int totalRecords = taskService.countTasks(keyword, priority, status, projectId);
        int totalPages = taskService.calculateTotalPages(totalRecords);

        List<Task> tasks = taskService.findTask(keyword, priority, status, projectId, currentPage);

        request.setAttribute("tasks", tasks);
        request.setAttribute("projects", projectService.getAllProjects());
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("contentPage", "/view/admin/task/task-form.jsp");
        request.getRequestDispatcher("/view/layout/main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        TaskResult result = null;

        switch (action) {
            case "add": {
                String taskName = request.getParameter("taskName");
                String description = request.getParameter("description");
                Date deadline = Date.valueOf(request.getParameter("deadline"));
                String status = request.getParameter("status");
                String priority = request.getParameter("priority");
                int projectId = Integer.parseInt(request.getParameter("projectId"));

                Task task = new Task(taskName, description, deadline, status, priority, projectId);
                result = taskService.addTask(task);
                break;
            }

            case "edit": {
                int taskId = Integer.parseInt(request.getParameter("taskId"));
                String taskName = request.getParameter("taskName");
                String description = request.getParameter("description");
                Date deadline = Date.valueOf(request.getParameter("deadline"));
                String status = request.getParameter("status");
                String priority = request.getParameter("priority");
                int projectId = Integer.parseInt(request.getParameter("projectId"));

                Task task = new Task(taskId, taskName, description, deadline, status, priority, projectId);
                result = taskService.updateTask(task);
                break;
            }

            case "delete": {
                int taskId = Integer.parseInt(request.getParameter("taskId"));
                result = taskService.deleteTask(taskId);
                break;
            }
        }

        request.getSession().setAttribute("result", result);
        request.getSession().setAttribute("action", action);
        response.sendRedirect(request.getContextPath() + "/task");
    }
}
