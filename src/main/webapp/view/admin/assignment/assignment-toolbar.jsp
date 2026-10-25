<%@page import="model.Employee"%>
<%@page import="model.Task"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Thanh tìm kiếm và nút thêm phân công. --%>

<div class="module-toolbar">
    <div class="toolbar-filters">
        <div class="input-group toolbar-search">
            <span class="input-group-text">
                <i class="bi bi-search"></i>
            </span>
            <input type="text" name="keyword" id="searchInput"
                   class="form-control" placeholder="Tìm kiếm phân công...">
        </div>

        <select name="employeeId"  id="employeeFilter" class="form-select toolbar-select-wide">
            <option value="">Tất cả nhân viên</option>
            <%
                List<Employee> toolbarEmployees = (List<Employee>) request.getAttribute("employees");

                if (toolbarEmployees != null) {
                    for (Employee employee : toolbarEmployees) {
            %>
            <option value="<%=employee.getEmployeeId()%>"><%=employee.getEmployeeName()%></option>
            <%
                    }
                }
            %>
        </select>

        <select name="taskId" id="taskFilter" class="form-select toolbar-select-wide">
            <option value="">Tất cả công việc</option>
            <%
                List<Task> toolbarTasks = (List<Task>) request.getAttribute("tasks");

                if (toolbarTasks != null) {
                    for (Task task : toolbarTasks) {
            %>
            <option value="<%=task.getTaskId()%>"><%=task.getTaskName()%></option>
            <%
                    }
                }
            %>
        </select>
    </div>

    <button type="button" class="btn btn-primary toolbar-add"
            data-bs-toggle="modal" data-bs-target="#AddEditModal"
            onclick="openAddModal()">
        <i class="bi bi-plus-lg me-1"></i> Thêm phân công
    </button>
</div>
