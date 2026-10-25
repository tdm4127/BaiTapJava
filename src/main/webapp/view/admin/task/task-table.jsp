<%@ page import="java.text.SimpleDateFormat" %>
<%-- Chỉ định dạng ngày khi hiển thị; dữ liệu ngày gốc dùng cho form giữ nguyên. --%>
<% SimpleDateFormat displayDate = new SimpleDateFormat("dd/MM/yyyy"); %>
<%@page import="model.Task"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Danh sách công việc với nút xem/sửa/xóa. --%>

<div class="table-card">
    <div class="table-responsive">
        <%-- Bảng hiển thị dữ liệu từ Servlet. --%>
<table class="table data-table task-table align-middle">
            <thead>
                <tr>
                    <th>Mã CV</th>
                    <th>Tên công việc</th>
                    <th>Dự án</th>
                    <th>Hạn chót</th>
                    <th>Trạng thái</th>
                    <th>Ưu tiên</th>
                    <th class="actions-head"></th>
                </tr>
            </thead>

            <tbody>
                <%
                    List<Task> tasks = (List<Task>) request.getAttribute("tasks");

                    if (tasks != null && !tasks.isEmpty()) {
                        for (Task task : tasks) {
                            String statusText = task.getStatus();
                            String statusClass = "status-pending";

                            if ("Pending".equals(task.getStatus()) || "Chưa bắt đầu".equals(task.getStatus())) {
                                statusText = "Chưa bắt đầu";
                            } else if ("In Progress".equals(task.getStatus()) || "Đang thực hiện".equals(task.getStatus())) {
                                statusText = "Đang thực hiện";
                                statusClass = "status-progress";
                            } else if ("Completed".equals(task.getStatus()) || "Hoàn thành".equals(task.getStatus())) {
                                statusText = "Hoàn thành";
                                statusClass = "status-completed";
                            }

                            String priorityText = task.getPriority();
                            String priorityClass = "priority-low";

                            if ("Low".equals(task.getPriority()) || "Thấp".equals(task.getPriority())) {
                                priorityText = "Thấp";
                            } else if ("Medium".equals(task.getPriority()) || "Trung bình".equals(task.getPriority())) {
                                priorityText = "Trung bình";
                                priorityClass = "priority-medium";
                            } else if ("High".equals(task.getPriority()) || "Cao".equals(task.getPriority())) {
                                priorityText = "Cao";
                                priorityClass = "priority-high";
                            }
                %>

                <tr class="task-row"
                    data-task-id="<%=task.getTaskId()%>"
                    data-name="<%=task.getTaskName()%>"
                    data-description="<%=task.getDescription() != null ? task.getDescription() : ""%>"
                    data-deadline="<%=task.getDeadline()%>"
                    data-status="<%=task.getStatus()%>"
                    data-priority="<%=task.getPriority()%>"
                    data-project-id="<%=task.getProjectId()%>"
                    data-project-name="<%=task.getProjectName() != null ? task.getProjectName() : ""%>">

                    <td><span class="id-badge"><%=String.format("TSK%03d", task.getTaskId())%></span></td>
                    <td><strong><%=task.getTaskName()%></strong></td>
                    <td><%=task.getProjectName()%></td>
                    <td class="date-cell"><%= task.getDeadline() == null ? "—" : displayDate.format(task.getDeadline()) %></td>
                    <td><span class="status-badge <%=statusClass%>"><%=statusText%></span></td>
                    <td><span class="priority-badge <%=priorityClass%>"><%=priorityText%></span></td>

                    <td class="actions-cell">
                        <div class="dropdown action-dropdown">
                            <button type="button" class="btn-table btn-more"
                                    data-bs-toggle="dropdown" aria-expanded="false" aria-label="Tùy chọn">
                                <i class="fa-solid fa-ellipsis"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <button type="button" class="dropdown-item"
                                            data-bs-toggle="modal" data-bs-target="#TaskViewModal"
                                            onclick="openViewModal(this)">
                                        <i class="fa-solid fa-eye me-2"></i>Xem chi tiết
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="dropdown-item"
                                            data-bs-toggle="modal" data-bs-target="#AddEditModal"
                                            onclick="openEditModal(this)">
                                        <i class="fa-solid fa-pen me-2"></i>Sửa
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="dropdown-item text-danger"
                                            data-bs-toggle="modal" data-bs-target="#DeleteModal"
                                            onclick="openDeleteModal(this)">
                                        <i class="fa-solid fa-trash me-2"></i>Xóa
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>

                <%
                        }
                    } else {
                %>

                <tr>
                    <td colspan="7" class="empty-row">
                        <i class="bi bi-inbox me-1"></i> Không tìm thấy công việc.
                    </td>
                </tr>

                <% } %>
            </tbody>
        </table>
    </div>
</div>

<div class="table-footer">
    <%
        Integer currentPage = (Integer) request.getAttribute("currentPage");
        Integer totalPages = (Integer) request.getAttribute("totalPages");
        Integer totalRecords = (Integer) request.getAttribute("totalRecords");
    %>

    <span>Tổng: <strong><%= totalRecords%></strong> công việc</span>

    <div class="pagination-simple">
        <button type="button" class="page-btn" aria-label="Trang trước"
                onclick="loadTasks(<%= currentPage - 1%>)"
                <%= currentPage <= 1 ? "disabled" : ""%>>
            <i class="fa-solid fa-chevron-left"></i>
        </button>

        <%
            for (int i = 1; i <= totalPages; i++) {
        %>
        <button type="button"
                class="page-btn <%= i == currentPage ? "active" : ""%>"
                onclick="loadTasks(<%= i%>)"><%= i%></button>
        <%
            }
        %>

        <button type="button" class="page-btn" aria-label="Trang sau"
                onclick="loadTasks(<%= currentPage + 1%>)"
                <%= currentPage >= totalPages ? "disabled" : ""%>>
            <i class="fa-solid fa-chevron-right"></i>
        </button>
    </div>
</div>
