<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.text.SimpleDateFormat, model.Task" %>
<%-- Hiển thị công việc được phân công và nút cập nhật trạng thái. --%>
<div class="employee-dashboard">
    <section id="employee-tasks" class="ed-view">
        <h1>Công việc của tôi</h1>
        <p class="ed-muted">Theo dõi công việc được giao và cập nhật tiến độ.</p>
        <div class="ed-panel">
            <div class="ed-panel-head">
                <div><h2>Danh sách công việc</h2><p>Các công việc nhân viên được phân công.</p></div>
            </div>
            <div class="ed-table-wrap">
                <table class="ed-table">
                    <thead>
                        <tr><th>Mã CV</th><th>Tên công việc</th><th>Dự án</th><th>Hạn chót</th><th>Trạng thái</th><th>Thao tác</th></tr>
                    </thead>
                    <tbody>
                        <%
                            // Duyệt danh sách công việc do MyTaskServlet truyền sang.
                            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                            if (tasks != null && !tasks.isEmpty()) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                for (Task task : tasks) {
                                    String status = task.getStatus();
                                    boolean done = "Completed".equals(status) || "Hoàn thành".equals(status);
                                    boolean doing = "In Progress".equals(status) || "Đang thực hiện".equals(status);
                                    String statusClass = done ? "done" : (doing ? "doing" : "todo");
                                    String statusText = done ? "Hoàn thành" : (doing ? "Đang thực hiện" : "Chưa bắt đầu");
                        %>
                        <tr>
                            <td><%= String.format("TSK%03d", task.getTaskId()) %></td>
                            <td><%= task.getTaskName() %></td>
                            <td><%= task.getProjectName() %></td>
                            <td><%= task.getDeadline() == null ? "—" : dateFormat.format(task.getDeadline()) %></td>
                            <td><span class="ed-status <%= statusClass %>"><%= statusText %></span></td>
                            <td>
                                <%-- Gửi mã công việc và thao tác về MyTaskServlet bằng POST. --%>
                                <% if (done) { %>
                                <button type="button" class="ed-button" disabled>Đã hoàn thành</button>
                                <% } else { %>
                                <form action="${pageContext.request.contextPath}/my-tasks" method="post" class="ed-action-form">
                                    <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                                    <% if (doing) { %>
                                    <button type="submit" name="action" value="finish" class="ed-button finish">Hoàn thành</button>
                                    <% } else { %>
                                    <button type="submit" name="action" value="start" class="ed-button">Bắt đầu</button>
                                    <% } %>
                                </form>
                                <% } %>
                            </td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr><td colspan="6" class="text-center">Chưa có công việc được phân công.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <%-- Trạng thái được lưu trong bảng Task nên Admin cũng nhìn thấy khi mở danh sách. --%>
        </div>
    </section>
</div>
