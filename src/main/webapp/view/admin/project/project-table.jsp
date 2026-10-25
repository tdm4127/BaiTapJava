<%@ page import="java.text.SimpleDateFormat" %>
<%-- Chỉ định dạng ngày khi hiển thị; dữ liệu ngày gốc dùng cho form giữ nguyên. --%>
<% SimpleDateFormat displayDate = new SimpleDateFormat("dd/MM/yyyy"); %>
<%@page import="model.Task"%>
<%@page import="model.Project"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Danh sách dự án và các nút thao tác. --%>

<div class="table-card">
    <div class="table-responsive">
        <%-- Bảng hiển thị dữ liệu từ Servlet. --%>
<table class="table data-table project-table align-middle">
            <thead>
                <tr>
                    <th>Mã DA</th>
                    <th>Tên dự án</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th>Số công việc</th>
                    <th>Tiến độ</th>
                    <th class="actions-head"></th>
                </tr>
            </thead>

            <tbody>
                <%
                    List<Project> projects = (List<Project>) request.getAttribute("projects");
                    List<Task> projectTasks = (List<Task>) request.getAttribute("tasks");

                    if (projects != null && !projects.isEmpty()) {
                        for (Project prj : projects) {
                            long duration = (prj.getEndDate().getTime() - prj.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
                            int taskCount = 0;
                            int completedCount = 0;

                            if (projectTasks != null) {
                                for (Task task : projectTasks) {
                                        if (task.getProjectId() == prj.getProjectId()) {
                                        taskCount++;
                                        if ("Completed".equals(task.getStatus()) || "Hoàn thành".equals(task.getStatus())) {
                                            completedCount++;
                                        }
                                    }
                                }
                            }

                            int progress = taskCount == 0 ? 0 : (completedCount * 100 / taskCount);
                %>

                <tr class="project-row"
                    data-id="<%=prj.getProjectId()%>"
                    data-name="<%=prj.getProjectName()%>"
                    data-start="<%=prj.getStartDate()%>"
                    data-end="<%=prj.getEndDate()%>"
                    data-duration="<%=duration%>"
                    data-task-count="<%=taskCount%>"
                    data-completed-count="<%=completedCount%>"
                    data-progress="<%=progress%>"
                    data-description="<%=prj.getDescription() != null ? prj.getDescription() : ""%>">

                    <td><span class="id-badge"><%=String.format("PRJ%03d", prj.getProjectId())%></span></td>
                    <td><strong><%=prj.getProjectName()%></strong></td>
                    <td class="date-cell"><%= prj.getStartDate() == null ? "—" : displayDate.format(prj.getStartDate()) %></td>
                    <td class="date-cell"><%= prj.getEndDate() == null ? "—" : displayDate.format(prj.getEndDate()) %></td>
                    <td><span class="task-count-badge"><%=taskCount%></span></td>
                    <td>
                        <div class="project-progress">
                            <div class="project-progress-track">
                                <div class="project-progress-bar" style="width: <%=progress%>%"></div>
                            </div>
                            <span><%=progress%>%</span>
                        </div>
                    </td>

                    <td class="actions-cell">
                        <div class="dropdown action-dropdown">
                            <button type="button" class="btn-table btn-more"
                                    data-bs-toggle="dropdown" aria-expanded="false" aria-label="Tùy chọn">
                                <i class="fa-solid fa-ellipsis"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <button type="button" class="dropdown-item"
                                            data-bs-toggle="modal" data-bs-target="#ProjectViewModal"
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
                        <i class="bi bi-inbox me-1"></i> Không tìm thấy dự án.
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

    <span>Tổng: <strong><%= totalRecords%></strong> dự án</span>

    <div class="pagination-simple">
        <button type="button" class="page-btn" aria-label="Trang trước"
                onclick="loadProjects(<%= currentPage - 1%>)"
                <%= currentPage <= 1 ? "disabled" : ""%>>
            <i class="fa-solid fa-chevron-left"></i>
        </button>

        <%
            for (int i = 1; i <= totalPages; i++) {
        %>
        <button type="button"
                class="page-btn <%= i == currentPage ? "active" : ""%>"
                onclick="loadProjects(<%= i%>)"><%= i%></button>
        <%
            }
        %>

        <button type="button" class="page-btn" aria-label="Trang sau"
                onclick="loadProjects(<%= currentPage + 1%>)"
                <%= currentPage >= totalPages ? "disabled" : ""%>>
            <i class="fa-solid fa-chevron-right"></i>
        </button>
    </div>
</div>
