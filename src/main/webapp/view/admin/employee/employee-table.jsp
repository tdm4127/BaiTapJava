<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Danh sách nhân viên và nút xem/sửa/xóa. --%>

<div class="table-card">
    <div class="table-responsive">
        <%-- Bảng hiển thị dữ liệu từ Servlet. --%>
<table class="table data-table employee-table align-middle">
            <thead>
                <tr>
                    <th>Mã NV</th>
                    <th>Họ và tên</th>
                    <th>Chức vụ</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th class="actions-head"></th>
                </tr>
            </thead>

            <tbody>
                <%
                    List<Employee> employees = (List<Employee>) request.getAttribute("employees");

                    if (employees != null && !employees.isEmpty()) {
                        for (Employee emp : employees) {
                %>

                <tr class="employee-row"
                    data-id="<%= emp.getEmployeeId()%>"
                    data-name="<%= emp.getEmployeeName()%>"
                    data-position="<%= emp.getPosition()%>"
                    data-email="<%= emp.getEmail() != null ? emp.getEmail() : ""%>"
                    data-phone="<%= emp.getPhoneNumber() != null ? emp.getPhoneNumber() : ""%>">

                    <td><span class="id-badge"><%= String.format("EMP%03d", emp.getEmployeeId())%></span></td>
                    <td><strong><%= emp.getEmployeeName()%></strong></td>
                    <td><%= emp.getPosition()%></td>
                    <td><%= emp.getEmail() != null ? emp.getEmail() : "—"%></td>
                    <td><%= emp.getPhoneNumber() != null ? emp.getPhoneNumber() : "—"%></td>

                    <td class="actions-cell">
                        <div class="dropdown action-dropdown">
                            <button type="button" class="btn-table btn-more"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa-solid fa-ellipsis"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <button type="button" class="dropdown-item"
                                            data-bs-toggle="modal" data-bs-target="#EmployeeViewModal"
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
                    <td colspan="6" class="empty-row">
                        <i class="bi bi-inbox me-1"></i> Không tìm thấy nhân viên.
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

        if (currentPage == null) {
            currentPage = 1;
        }
        if (totalPages == null) {
            totalPages = 0;
        }
        if (totalRecords == null) {
            totalRecords = 0;
        }
    %>

    <span>Tổng: <strong><%= totalRecords%></strong> nhân viên</span>

    <div class="pagination-simple">
        <button type="button" class="page-btn" aria-label="Trang trước"
                onclick="loadEmployees(<%= currentPage - 1%>)"
                <%= currentPage <= 1 ? "disabled" : ""%>>
            <i class="fa-solid fa-chevron-left"></i>
        </button>

        <%
            for (int i = 1; i <= totalPages; i++) {
        %>
        <button type="button"
                class="page-btn <%= i == currentPage ? "active" : ""%>"
                onclick="loadEmployees(<%= i%>)"><%= i%></button>
        <%
            }
        %>

        <button type="button" class="page-btn" aria-label="Trang sau"
                onclick="loadEmployees(<%= currentPage + 1%>)"
                <%= currentPage >= totalPages ? "disabled" : ""%>>
            <i class="fa-solid fa-chevron-right"></i>
        </button>
    </div>
</div>
