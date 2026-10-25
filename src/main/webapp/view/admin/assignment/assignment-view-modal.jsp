<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Popup xem thông tin phân công. --%>

<div class="modal fade" id="AssignmentViewModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content app-modal">
            <div class="modal-header">
                <h5 class="modal-title">Chi tiết phân công</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
            </div>
            <div class="modal-body">
                <div class="view-head">
                    <span class="view-code" id="viewAssignmentCode"></span>
                    <div><h5 id="viewAssignmentTask"></h5><span class="text-muted">Thông tin phân công công việc</span></div>
                </div>
                <div class="view-grid">
                    <div><small>Nhân viên</small><strong id="viewAssignmentEmployee"></strong></div>
                    <div><small>Công việc</small><strong id="viewAssignmentTaskBox"></strong></div>
                    <div><small>Ngày phân công</small><strong id="viewAssignmentDate"></strong></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light border" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
