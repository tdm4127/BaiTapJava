<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Ô tìm kiếm và nút mở form thêm nhân viên. --%>

<div class="module-toolbar">
    <div class="toolbar-filters">
        <div class="input-group toolbar-search">
            <span class="input-group-text">
                <i class="bi bi-search"></i>
            </span>
            <input type="text" name="keyword" id="searchInput"
                   class="form-control" placeholder="Tìm kiếm nhân viên...">
        </div>

        <select name="position" id="positionFilter" class="form-select toolbar-select-wide">
            <option value="">Tất cả chức vụ</option>
            <option value="Quản lý">Quản lý</option>
            <option value="Lập trình viên">Lập trình viên</option>
            <option value="Kiểm thử viên">Kiểm thử viên</option>
            <option value="Thiết kế">Thiết kế</option>
            <option value="Phân tích viên">Phân tích viên</option>
        </select>
    </div>

    <button type="button" class="btn btn-primary toolbar-add"
            data-bs-toggle="modal" data-bs-target="#AddEditModal"
            onclick="openAddModal()">
        <i class="bi bi-plus-lg me-1"></i> Thêm nhân viên
    </button>
</div>
