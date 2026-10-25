<%@ page import="java.text.SimpleDateFormat" %>
<%-- Chỉ định dạng ngày khi hiển thị; dữ liệu ngày gốc dùng cho form giữ nguyên. --%>
<% SimpleDateFormat displayDate = new SimpleDateFormat("dd/MM/yyyy"); %>
<%@page import="model.Assignment"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Bảng danh sách phân công, các nút thao tác. --%>

<div class="table-card">
    <div class="table-responsive">
        <%-- Bảng hiển thị dữ liệu từ Servlet. --%>
<table class="table data-table assignment-table align-middle">
            <thead>
                <tr>
                    <th>Mã PC</th>
                    <th>Công việc</th>
                    <th>Nhân viên</th>
                    <th>Ngày phân công</th>
                    <th class="actions-head"></th>
                </tr>
            </thead>

            <tbody>
                <%
                    List<Assignment> assignments = (List<Assignment>) request.getAttribute("assignments");

                    if (assignments != null && !assignments.isEmpty()) {
                        for (Assignment asn : assignments) {
                %>

                <tr class="assignment-row"
                    data-assignment-id="<%=asn.getAssignmentId()%>"
                    data-task-id="<%=asn.getTaskId()%>"
                    data-employee-id="<%=asn.getEmployeeId()%>"
                    data-assigned-date="<%=asn.getAssignedDate()%>"
                    data-task-name="<%=asn.getTaskName() != null ? asn.getTaskName() : ""%>"
                    data-employee-name="<%=asn.getEmployeeName() != null ? asn.getEmployeeName() : ""%>">

                    <td><span class="id-badge"><%=String.format("ASN%03d", asn.getAssignmentId())%></span></td>
                    <td><strong><%=asn.getTaskName()%></strong></td>
                    <td><%=asn.getEmployeeName()%></td>
                    <td class="date-cell"><%= asn.getAssignedDate() == null ? "—" : displayDate.format(asn.getAssignedDate()) %></td>

                    <td class="actions-cell">
                        <div class="dropdown action-dropdown">
                            <button type="button" class="btn-table btn-more"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa-solid fa-ellipsis"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <button type="button" class="dropdown-item"
                                            data-bs-toggle="modal" data-bs-target="#AssignmentViewModal"
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
                    <td colspan="5" class="empty-row">
                        <i class="bi bi-inbox me-1"></i> Không tìm thấy phân công.
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

    <span>Tổng: <strong><%= totalRecords%></strong> phân công</span>

    <div class="pagination-simple">
        <button type="button" class="page-btn" aria-label="Trang trước"
                onclick="loadAssignments(<%= currentPage - 1%>)"
                <%= currentPage <= 1 ? "disabled" : ""%>>
            <i class="fa-solid fa-chevron-left"></i>
        </button>

        <%
            for (int i = 1; i <= totalPages; i++) {
        %>
        <button type="button"
                class="page-btn <%= i == currentPage ? "active" : ""%>"
                onclick="loadAssignments(<%= i%>)"><%= i%></button>
        <%
            }
        %>

        <button type="button" class="page-btn" aria-label="Trang sau"
                onclick="loadAssignments(<%= currentPage + 1%>)"
                <%= currentPage >= totalPages ? "disabled" : ""%>>
            <i class="fa-solid fa-chevron-right"></i>
        </button>
    </div>
</div>
