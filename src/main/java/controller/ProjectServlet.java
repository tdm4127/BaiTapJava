// Servlet dự án: nhận thao tác quản lý dự án.
package controller;

import enums.ProjectResult;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.Project;
import service.ProjectService;
import service.TaskService;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {

    private ProjectService prjService = new ProjectService();
    private TaskService taskService = new TaskService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String keyword = request.getParameter("keyword");
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");
        String pageParam = request.getParameter("page");

        Date startDate = null;
        Date endDate = null;

        if (startDateParam != null && !startDateParam.trim().isEmpty()) {
            startDate = Date.valueOf(startDateParam);
        }

        if (endDateParam != null && !endDateParam.trim().isEmpty()) {
            endDate = Date.valueOf(endDateParam);
        }

        int currentPage = pageParam == null ? 1 : Integer.parseInt(pageParam);

        int totalRecords = prjService.countProjects(keyword, startDate, endDate);
        int totalPages = prjService.calculateTotalPages(totalRecords);

        List<Project> projects = prjService.findProject(keyword, startDate, endDate, currentPage);

        request.setAttribute("projects", projects);
        request.setAttribute("tasks", taskService.getAllTasks());
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("contentPage", "/view/admin/project/project-form.jsp");
        request.getRequestDispatcher("/view/layout/main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        String projectName = request.getParameter("projectName");

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        Date startDate = null;
        Date endDate = null;

        if (startDateStr != null && !startDateStr.trim().isEmpty()) {
            startDate = Date.valueOf(startDateStr);
        }

        if (endDateStr != null && !endDateStr.trim().isEmpty()) {
            endDate = Date.valueOf(endDateStr);
        }

        String description = request.getParameter("description");
        ProjectResult result = null;

        switch (action) {
            case "add":
                Project prj = new Project(projectName, startDate, endDate, description);
                result = prjService.addProject(prj);
                break;
            case "edit":
                int projectIdEdit = Integer.parseInt(request.getParameter("projectId"));
                Project prjEdit = new Project(projectIdEdit, projectName, startDate, endDate, description);
                result = prjService.updateProject(prjEdit);
                break;
            case "delete":
                int projectIdDelete = Integer.parseInt(request.getParameter("projectId"));
                result = prjService.deleteProject(projectIdDelete);
                break;
        }

        request.getSession().setAttribute("result", result);
        request.getSession().setAttribute("action", action);
        response.sendRedirect(request.getContextPath() + "/project");
    }
}
