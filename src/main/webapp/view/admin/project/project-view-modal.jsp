<%@ page import="java.text.SimpleDateFormat" %>
<%-- Chỉ định dạng ngày khi hiển thị; dữ liệu ngày gốc dùng cho form giữ nguyên. --%>
<% SimpleDateFormat displayDate = new SimpleDateFormat("dd/MM/yyyy"); %>
<%@page import="model.Task"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Popup xem chi tiết dự án và công việc liên quan. --%>

<div class="modal fade" id="ProjectViewModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl">
        <div class="modal-content app-modal">
            <div class="modal-header">
                <h5 class="modal-title">Chi tiết dự án</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
            </div>

            <div class="modal-body p-0">
                <div class="project-detail-layout">
                    <section class="project-detail-info">
                        <div class="project-detail-title">
                            <span class="project-detail-icon"><i class="bi bi-folder-fill"></i></span>
                            <div>
                                <div class="view-code" id="viewProjectCode"></div>
                                <h5 id="viewProjectName"></h5>
                            </div>
                        </div>

                        <div class="project-detail-meta">
                            <div class="project-detail-meta-row">
                                <span>Ngày bắt đầu:</span><strong id="viewProjectStart"></strong>
                            </div>
                            <div class="project-detail-meta-row">
                                <span>Ngày kết thúc:</span><strong id="viewProjectEnd"></strong>
                            </div>
                            <div class="project-detail-meta-row">
                                <span>Thời lượng:</span><strong id="viewProjectDuration"></strong>
                            </div>
                            <div class="project-detail-meta-row">
                                <span>Số công việc:</span><strong id="viewProjectTaskCount"></strong>
                            </div>
                            <div class="project-detail-meta-row">
                                <span>Đã hoàn thành:</span><strong id="viewProjectCompleted"></strong>
                            </div>
                            <div class="project-detail-meta-row">
                                <span>Tiến độ:</span><strong id="viewProjectProgress"></strong>
                            </div>
                        </div>

                        <div class="project-detail-description">
                            <small>Mô tả</small>
                            <p id="viewProjectDescription"></p>
                        </div>
                    </section>

                    <section class="project-detail-tasks">
                        <div class="project-detail-tasks-head">
                            <h6>Danh sách công việc</h6>
                            <span id="viewProjectTaskTotal"></span>
                        </div>

                        <div class="table-responsive">
                            <%-- Bảng hiển thị dữ liệu từ Servlet. --%>
<table class="table project-task-view-table align-middle">
                                <thead>
                                    <tr>
                                        <th>Mã CV</th>
                                        <th>Tên công việc</th>
                                        <th>Hạn chót</th>
                                        <th>Trạng thái</th>
                                        <th>Ưu tiên</th>
                                    </tr>
                                </thead>
                                <tbody id="viewProjectTaskList"></tbody>
                            </table>
                        </div>
                    </section>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light border" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<template id="projectTaskTemplates">
    <%
        List<Task> viewTasks = (List<Task>) request.getAttribute("tasks");
        if (viewTasks != null) {
            for (Task task : viewTasks) {
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
    <tr data-project-id="<%=task.getProjectId()%>">
        <td><span class="id-badge"><%=String.format("TSK%03d", task.getTaskId())%></span></td>
        <td><strong><%=task.getTaskName()%></strong></td>
        <td class="date-cell"><%= task.getDeadline() == null ? "—" : displayDate.format(task.getDeadline()) %></td>
        <td><span class="status-badge <%=statusClass%>"><%=statusText%></span></td>
        <td><span class="priority-badge <%=priorityClass%>"><%=priorityText%></span></td>
    </tr>
    <%
            }
        }
    %>
</template>
