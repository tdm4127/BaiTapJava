<%@page import="model.Project"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Tìm kiếm, lọc và thêm công việc. --%>

<div class="module-toolbar task-toolbar">
    <div class="toolbar-filters">
        <div class="input-group toolbar-search">
            <span class="input-group-text">
                <i class="bi bi-search"></i>
            </span>
            <input type="text" name="keyword" id="searchInput"
                   class="form-control" placeholder="Tìm kiếm công việc...">
        </div>

        <select name="priority" id="priorityFilter" class="form-select toolbar-select">
            <option value="">Độ ưu tiên</option>
            <option value="Thấp">Thấp</option>
            <option value="Trung bình">Trung bình</option>
            <option value="Cao">Cao</option>
        </select>

        <select name="status" id="statusFilter" class="form-select toolbar-select">
            <option value="">Trạng thái</option>
            <option value="Chưa bắt đầu">Chưa bắt đầu</option>
            <option value="Đang thực hiện">Đang thực hiện</option>
            <option value="Hoàn thành">Hoàn thành</option>
        </select>

        <select name="projectId" id="projectFilter" class="form-select toolbar-select-wide">
            <option value="">Tất cả dự án</option>
            <%
                List<Project> toolbarProjects = (List<Project>) request.getAttribute("projects");

                if (toolbarProjects != null) {
                    for (Project project : toolbarProjects) {
            %>
            <option value="<%=project.getProjectId()%>"><%=project.getProjectName()%></option>
            <%
                    }
                }
            %>
        </select>
    </div>

    <button type="button" class="btn btn-primary toolbar-add"
            data-bs-toggle="modal" data-bs-target="#AddEditModal"
            onclick="openAddModal()">
        <i class="bi bi-plus-lg me-1"></i> Thêm công việc
    </button>
</div>
