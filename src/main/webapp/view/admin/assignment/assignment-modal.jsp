<%@page import="model.Employee"%>
<%@page import="model.Task"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Form thêm/sửa phân công, gửi dữ liệu về Servlet. --%>

<div class="modal fade" id="AddEditModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content app-modal">
            <%-- Form gửi dữ liệu về Servlet. --%>
<form method="post" action="assignment" id="assignment-form">
                <input type="hidden" name="assignmentId" id="inputAssignmentId">
                <input type="hidden" name="action" id="inputAction">

                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Thêm phân công</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                </div>

                <div class="modal-body">
                    <div class="form-field">
                        <label class="form-label">Mã phân công</label>
                        <input type="text" id="displayId" class="form-control"
                               placeholder="Tự động tạo" readonly>
                    </div>

                    <div class="form-field">
                        <label class="form-label">
                            Công việc <span class="required-mark">*</span>
                        </label>
                        <select name="taskId" id="inputTaskId" class="form-select" required>
                            <option value="" selected disabled>-- Chọn công việc --</option>
                            <%
                                List<Task> tasks = (List<Task>) request.getAttribute("tasks");

                                if (tasks != null) {
                                    for (Task task : tasks) {
                            %>
                            <option value="<%=task.getTaskId()%>">
                                <%=String.format("TSK%03d", task.getTaskId())%> - <%=task.getTaskName()%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-field">
                        <label class="form-label">
                            Nhân viên <span class="required-mark">*</span>
                        </label>
                        <select name="employeeId" id="inputEmployeeId" class="form-select" required>
                            <option value="" selected disabled>-- Chọn nhân viên --</option>
                            <%
                                List<Employee> employees = (List<Employee>) request.getAttribute("employees");

                                if (employees != null) {
                                    for (Employee employee : employees) {
                            %>
                            <option value="<%=employee.getEmployeeId()%>">
                                <%=String.format("EMP%03d", employee.getEmployeeId())%> - <%=employee.getEmployeeName()%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-field">
                        <label class="form-label">
                            Ngày phân công <span class="required-mark">*</span>
                        </label>
                        <input type="date" name="assignedDate" id="inputAssignedDate"
                               class="form-control" required>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-light border" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-check-lg me-1"></i> Lưu
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
