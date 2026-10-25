<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Popup xem chi tiết công việc. --%>

<div class="modal fade" id="TaskViewModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content app-modal">
            <div class="modal-header">
                <h5 class="modal-title">Chi tiết công việc</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
            </div>
            <div class="modal-body">
                <div class="view-head">
                    <span class="view-code" id="viewTaskCode"></span>
                    <div><h5 id="viewTaskName"></h5><span class="text-muted" id="viewTaskProject"></span></div>
                </div>
                <div class="view-grid">
                    <div><small>Hạn chót</small><strong id="viewTaskDeadline"></strong></div>
                    <div><small>Trạng thái</small><strong id="viewTaskStatus"></strong></div>
                    <div><small>Độ ưu tiên</small><strong id="viewTaskPriority"></strong></div>
                </div>
                <div class="view-description">
                    <small>Mô tả</small>
                    <p id="viewTaskDescription"></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light border" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
