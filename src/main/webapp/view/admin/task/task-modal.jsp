<%@page import="model.Project"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Form thêm/sửa công việc, gửi về TaskServlet. --%>

<div class="modal fade" id="AddEditModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content app-modal">
            <%-- Form gửi dữ liệu về Servlet. --%>
<form method="post" action="task" id="task-form">
                <input type="hidden" name="taskId" id="inputTaskId">
                <input type="hidden" name="action" id="inputAction">

                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Thêm công việc</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                </div>

                <div class="modal-body">
                    <div class="form-field">
                        <label class="form-label">Mã công việc</label>
                        <input type="text" id="displayId" class="form-control"
                               placeholder="Tự động tạo" readonly>
                    </div>

                    <div class="form-field">
                        <label class="form-label">
                            Tên công việc <span class="required-mark">*</span>
                        </label>
                        <input type="text" id="inputTaskName" name="taskName"
                               class="form-control" placeholder="Nhập tên công việc" required>
                    </div>

                    <div class="form-field">
                        <label class="form-label">Mô tả</label>
                        <textarea id="inputDescription" name="description"
                                  rows="3" class="form-control"
                                  placeholder="Nhập mô tả công việc"></textarea>
                    </div>

                    <div class="form-field">
                        <label class="form-label">
                            Hạn chót <span class="required-mark">*</span>
                        </label>
                        <input type="date" id="inputDeadline" name="deadline"
                               class="form-control" required>
                    </div>

                    <div class="row g-3">
                        <div class="col-md-6">
                            <div class="form-field">
                                <label class="form-label">
                                    Trạng thái <span class="required-mark">*</span>
                                </label>
                                <select name="status" id="inputStatus" class="form-select" required>
                                    <option value="" selected disabled>-- Chọn trạng thái --</option>
                                    <option value="Chưa bắt đầu">Chưa bắt đầu</option>
                                    <option value="Đang thực hiện">Đang thực hiện</option>
                                    <option value="Hoàn thành">Hoàn thành</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-field">
                                <label class="form-label">
                                    Độ ưu tiên <span class="required-mark">*</span>
                                </label>
                                <select name="priority" id="inputPriority" class="form-select" required>
                                    <option value="" selected disabled>-- Chọn độ ưu tiên --</option>
                                    <option value="Thấp">Thấp</option>
                                    <option value="Trung bình">Trung bình</option>
                                    <option value="Cao">Cao</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-field">
                        <label class="form-label">
                            Dự án <span class="required-mark">*</span>
                        </label>
                        <select id="inputProjectId" name="projectId" class="form-select" required>
                            <option value="" selected disabled>-- Chọn dự án --</option>
                            <%
                                List<Project> projects = (List<Project>) request.getAttribute("projects");

                                if (projects != null) {
                                    for (Project project : projects) {
                            %>
                            <option value="<%=project.getProjectId()%>">
                                <%=String.format("PRJ%03d", project.getProjectId())%> - <%=project.getProjectName()%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
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
