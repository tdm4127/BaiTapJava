<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Popup xác nhận xóa, dùng chung cho các trang quản lý. --%>

<div class="modal fade" id="DeleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-sm">
        <div class="modal-content app-modal">
            <div class="modal-header">
                <h5 class="modal-title">Xác nhận xóa</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
            </div>

            <div class="modal-body">
                <div class="delete-icon">
                    <i class="bi bi-trash3"></i>
                </div>
                <p id="deleteMessage" class="delete-message">Bạn có chắc muốn xóa dữ liệu này?</p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light border" data-bs-dismiss="modal">Hủy</button>
                <button type="submit" class="btn btn-danger" form="deleteForm">
                    <i class="bi bi-trash3 me-1"></i> Xóa
                </button>
            </div>

            <form id="deleteForm" method="post">
                <input type="hidden" id="deleteId">
                <input type="hidden" name="action" value="delete">
            </form>
        </div>
    </div>
</div>
