<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Popup xem chi tiết nhân viên. --%>

<div class="modal fade" id="EmployeeViewModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content app-modal">
            <div class="modal-header">
                <h5 class="modal-title">Chi tiết nhân viên</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
            </div>
            <div class="modal-body">
                <div class="view-head">
                    <span class="view-code" id="viewEmployeeCode"></span>
                    <div><h5 id="viewEmployeeName"></h5><span class="text-muted" id="viewEmployeePosition"></span></div>
                </div>
                <div class="view-grid">
                    <div><small>Chức vụ</small><strong id="viewEmployeePositionBox"></strong></div>
                    <div><small>Email</small><strong id="viewEmployeeEmail"></strong></div>
                    <div><small>Số điện thoại</small><strong id="viewEmployeePhone"></strong></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light border" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
