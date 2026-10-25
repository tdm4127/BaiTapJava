// Đổi ngày từ yyyy-MM-dd sang dd/MM/yyyy khi xem chi tiết.
function formatDate(value) {
    return value ? value.split("-").reverse().join("/") : "—";
}

// Xử lý thao tác giao diện phân công.
function openViewModal(button) {
    const row = button.closest(".assignment-row");

    document.getElementById("viewAssignmentCode").innerText = "ASN" + String(row.dataset.assignmentId).padStart(3, "0");
    document.getElementById("viewAssignmentTask").innerText = row.dataset.taskName;
    document.getElementById("viewAssignmentTaskBox").innerText = row.dataset.taskName;
    document.getElementById("viewAssignmentEmployee").innerText = row.dataset.employeeName;
    document.getElementById("viewAssignmentDate").innerText = formatDate(row.dataset.assignedDate);
}

function openAddModal() {
    document.getElementById("assignment-form").reset();
    document.getElementById("inputAction").value = "add";
    document.getElementById("modalTitle").innerText = "Thêm phân công";
}

function openEditModal(button) {
    const row = button.closest(".assignment-row");

    document.getElementById("inputAction").value = "edit";
    document.getElementById("modalTitle").innerText = "Cập nhật phân công";
    document.getElementById("displayId").value = "ASN" + String(row.dataset.assignmentId).padStart(3, "0");

    document.getElementById("inputAssignmentId").value = row.dataset.assignmentId;
    document.getElementById("inputTaskId").value = row.dataset.taskId;
    document.getElementById("inputEmployeeId").value = row.dataset.employeeId;
    document.getElementById("inputAssignedDate").value = row.dataset.assignedDate;
}

function openDeleteModal(button) {
    const row = button.closest(".assignment-row");
    const asnCode = "ASN" + String(row.dataset.assignmentId).padStart(3, "0");

    document.getElementById("deleteMessage").innerText =
            "Bạn có chắc muốn xóa phân công " + asnCode + "?";

    const deleteId = document.getElementById("deleteId");
    deleteId.name = "assignmentId";
    deleteId.value = row.dataset.assignmentId;

    document.getElementById("deleteForm").action = "assignment";
}

let searchTimer;

function loadAssignments(page = 1) {

    const keyword = document.getElementById("searchInput").value;
    const employeeId = document.getElementById("employeeFilter").value;
    const taskId = document.getElementById("taskFilter").value;

    fetch("assignment?keyword=" + encodeURIComponent(keyword)
            + "&employeeId=" + encodeURIComponent(employeeId)
            + "&taskId=" + encodeURIComponent(taskId)
            + "&page=" + page)
            .then(function (res) {
                return res.text();
            })
            .then(function (html) {

                const parser = new DOMParser();
                const doc = parser.parseFromString(html, "text/html");

                const newTable =
                        doc.querySelector(".page-container .table-card");

                const currentTable =
                        document.querySelector(".page-container .table-card");

                if (newTable && currentTable) {
                    currentTable.outerHTML = newTable.outerHTML;
                }

                const newFooter =
                        doc.querySelector(".page-container .table-footer");

                const currentFooter =
                        document.querySelector(".page-container .table-footer");

                if (newFooter && currentFooter) {
                    currentFooter.outerHTML = newFooter.outerHTML;
                }
            });
}

document.getElementById("searchInput")
        .addEventListener("input", function () {

            clearTimeout(searchTimer);

            searchTimer = setTimeout(function () {
                loadAssignments(1);
            }, 300);
        });

document.getElementById("employeeFilter").addEventListener("change", function () { loadAssignments(1); });
document.getElementById("taskFilter").addEventListener("change", function () { loadAssignments(1); });
