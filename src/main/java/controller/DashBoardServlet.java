// Chọn trang Dashboard theo quyền và mục điều hướng.
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.DashBoardService;
import dao.TaskDao;
import java.util.List;
import model.Task;

@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {

    private DashBoardService dashboardService = new DashBoardService();
    private TaskDao taskDao = new TaskDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // AuthFilter đã kiểm tra đăng nhập và quyền truy cập.
        HttpSession session = request.getSession(false);

        String role = (String) session.getAttribute("role");
        if ("admin".equals(role)) {

            request.setAttribute("employeeCount", dashboardService.countEmployees());
            request.setAttribute("projectCount", dashboardService.countProjects());
            request.setAttribute("taskCount", dashboardService.countTasks());
            request.setAttribute("contentPage", "/view/admin/admin-dashboard.jsp");
        } else if ("employee".equals(role)) {
            // Đếm trạng thái các công việc được giao cho nhân viên đang đăng nhập.
            int employeeId = (Integer) session.getAttribute("employeeId");
            List<Task> tasks = taskDao.findByEmployeeId(employeeId);
            int notStarted = 0, inProgress = 0, completed = 0;
            for (Task task : tasks) {
                String status = task.getStatus();
                if ("Chưa bắt đầu".equals(status) || "Pending".equals(status)) {
                    notStarted++;
                } else if ("Đang thực hiện".equals(status) || "In Progress".equals(status)) {
                    inProgress++;
                } else if ("Hoàn thành".equals(status) || "Completed".equals(status)) {
                    completed++;
                }
            }
            request.setAttribute("notStartedCount", notStarted);
            request.setAttribute("inProgressCount", inProgress);
            request.setAttribute("completedCount", completed);
            request.setAttribute("contentPage", "/view/employee/employee-dashboard.jsp");
        }

        // Dùng chung Header, Sidebar và khung main.jsp.
        request.getRequestDispatcher("/view/layout/main.jsp").forward(request, response);
    }
}
