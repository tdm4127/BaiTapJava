<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Tìm kiếm và mở form thêm dự án. --%>

<div class="module-toolbar">
    <div class="toolbar-filters">
        <div class="input-group toolbar-search">
            <span class="input-group-text">
                <i class="bi bi-search"></i>
            </span>
            <input type="text" name="keyword" id="searchInput"
                   class="form-control" placeholder="Tìm kiếm dự án...">
        </div>

        <div class="input-group toolbar-date">
            <span class="input-group-text">Từ ngày</span>
            <input type="date" name="startDate" id="startDateFilter" class="form-control">
        </div>

        <div class="input-group toolbar-date">
            <span class="input-group-text">Đến ngày</span>
            <input type="date" name="endDate" id="endDateFilter" class="form-control">
        </div>
    </div>

    <button type="button" class="btn btn-primary toolbar-add"
            data-bs-toggle="modal" data-bs-target="#AddEditModal"
            onclick="openAddModal()">
        <i class="bi bi-plus-lg me-1"></i> Thêm dự án
    </button>
</div>
