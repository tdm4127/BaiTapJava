// Servlet phân công: nhận yêu cầu và chuyển cho Service.
package controller;

import enums.AssignmentResult;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.Assignment;
import service.AssignmentService;
import service.EmployeeService;
import service.TaskService;

@WebServlet(name = "AssignmentServlet", urlPatterns = {"/assignment"})
public class AssignmentServlet extends HttpServlet {

    private AssignmentService assignmentService = new AssignmentService();
    private TaskService taskService = new TaskService();
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String keyword = request.getParameter("keyword");
        String employeeIdParam = request.getParameter("employeeId");
        String taskIdParam = request.getParameter("taskId");
        String pageParam = request.getParameter("page");

        Integer employeeId = null;
        Integer taskId = null;

        if (employeeIdParam != null && !employeeIdParam.trim().isEmpty()) {
            employeeId = Integer.valueOf(employeeIdParam);
        }

        if (taskIdParam != null && !taskIdParam.trim().isEmpty()) {
            taskId = Integer.valueOf(taskIdParam);
        }

        int currentPage = pageParam == null ? 1 : Integer.parseInt(pageParam);

        int totalRecords = assignmentService.countAssignments(keyword, employeeId, taskId);
        int totalPages = assignmentService.calculateTotalPages(totalRecords);

        List<Assignment> assignments = assignmentService.findAssignment(keyword, employeeId, taskId, currentPage);

        request.setAttribute("assignments", assignments);
        request.setAttribute("tasks", taskService.getAllTasks());
        request.setAttribute("employees", employeeService.getAllEmployees());
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("contentPage", "/view/admin/assignment/assignment-form.jsp");

        request.getRequestDispatcher("/view/layout/main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        AssignmentResult result = null;

        switch (action) {
            case "add": {
                int taskId = Integer.parseInt(request.getParameter("taskId"));
                int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                Date assignedDate = Date.valueOf(request.getParameter("assignedDate"));

                Assignment assignment = new Assignment(taskId, employeeId, assignedDate);
                result = assignmentService.addAssignment(assignment);
                break;
            }

            case "edit": {
                int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
                int taskId = Integer.parseInt(request.getParameter("taskId"));
                int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                Date assignedDate = Date.valueOf(request.getParameter("assignedDate"));

                Assignment assignment = new Assignment(assignmentId, taskId, employeeId, assignedDate);
                result = assignmentService.updateAssignment(assignment);
                break;
            }

            case "delete": {
                int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
                result = assignmentService.deleteAssignment(assignmentId);
                break;
            }
        }

        request.getSession().setAttribute("result", result);
        request.getSession().setAttribute("action", action);
        response.sendRedirect(request.getContextPath() + "/assignment");
    }
}
