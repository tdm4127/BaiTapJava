// Hiển thị các công việc được phân công cho nhân viên đang đăng nhập.
package controller;

import dao.TaskDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Task;

@WebServlet("/my-tasks")
public class MyTaskServlet extends HttpServlet {

    private TaskDao taskDao = new TaskDao();

    // GET: Lấy danh sách công việc của nhân viên để hiển thị trên trang.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // AuthFilter đã xác nhận tài khoản Employee.
        HttpSession session = request.getSession(false);
        // Chỉ lấy các công việc được phân công cho tài khoản đang đăng nhập.
        Integer employeeId = (Integer) session.getAttribute("employeeId");
        List<Task> tasks = taskDao.findByEmployeeId(employeeId);

        request.setAttribute("tasks", tasks);
        request.setAttribute("contentPage", "/view/employee/my-tasks.jsp");
        request.getRequestDispatcher("/view/layout/main.jsp").forward(request, response);
    }

    // POST: Nhận nút Bắt đầu hoặc Hoàn thành và cập nhật trạng thái công việc.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        // DAO chỉ sửa Task đã phân công cho employeeId trong Session.
        int employeeId = (Integer) session.getAttribute("employeeId");
        taskDao.updateMyTaskStatus(taskId, employeeId, action);

        // Mở lại danh sách để hiển thị trạng thái mới nhất từ database.
        response.sendRedirect(request.getContextPath() + "/my-tasks");
    }

}
