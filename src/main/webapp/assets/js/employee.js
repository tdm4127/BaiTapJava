// Xử lý popup và nút thao tác nhân viên.
function openViewModal(button) {
    const row = button.closest(".employee-row");

    document.getElementById("viewEmployeeCode").innerText = "EMP" + String(row.dataset.id).padStart(3, "0");
    document.getElementById("viewEmployeeName").innerText = row.dataset.name;
    document.getElementById("viewEmployeePosition").innerText = row.dataset.position;
    document.getElementById("viewEmployeePositionBox").innerText = row.dataset.position;
    document.getElementById("viewEmployeeEmail").innerText = row.dataset.email || "—";
    document.getElementById("viewEmployeePhone").innerText = row.dataset.phone || "—";
}

function openAddModal() {
    document.getElementById("employee-form").reset();
    document.getElementById("inputAction").value = "add";
    document.getElementById("modalTitle").innerText = "Thêm nhân viên";
    document.getElementById("displayId").value = "";
    document.getElementById("inputEmployeeId").value = "";
}

function openEditModal(button) {
    const row = button.closest(".employee-row");

    document.getElementById("inputAction").value = "edit";
    document.getElementById("modalTitle").innerText = "Cập nhật nhân viên";
    document.getElementById("displayId").value = "EMP" + String(row.dataset.id).padStart(3, "0");
    document.getElementById("inputEmployeeId").value = row.dataset.id;
    document.getElementById("inputEmployeeName").value = row.dataset.name;
    document.getElementById("inputPosition").value = row.dataset.position;
    document.getElementById("inputEmail").value = row.dataset.email;
    document.getElementById("inputPhone").value = row.dataset.phone;
}

function openDeleteModal(button) {
    const row = button.closest(".employee-row");
    const empCode = "EMP" + String(row.dataset.id).padStart(3, "0");

    document.getElementById("deleteMessage").innerText =
            "Bạn có chắc muốn xóa nhân viên " + empCode + "?";

    const deleteId = document.getElementById("deleteId");
    deleteId.name = "employeeId";
    deleteId.value = row.dataset.id;

    document.getElementById("deleteForm").action = "employee";
}

let searchTimer;

const searchInput = document.getElementById("searchInput");
const positionFilter = document.getElementById("positionFilter");

function loadEmployees(page = 1) {
    const keyword = searchInput.value;
    const position = positionFilter.value;

    fetch("employee?keyword=" + encodeURIComponent(keyword)
            + "&position=" + encodeURIComponent(position)
            + "&page=" + page)
            .then(res => res.text())
            .then(html => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(html, "text/html");

                const newTable = doc.querySelector(".page-container .table-card");
                const currentTable = document.querySelector(".page-container .table-card");

                if (newTable && currentTable) {
                    currentTable.outerHTML = newTable.outerHTML;
                }

                const newFooter = doc.querySelector(".page-container .table-footer");
                const currentFooter = document.querySelector(".page-container .table-footer");

                if (newFooter && currentFooter) {
                    currentFooter.outerHTML = newFooter.outerHTML;
                }
            });
}

searchInput.addEventListener("input", function () {
    clearTimeout(searchTimer);
    searchTimer = setTimeout(function () {
        loadEmployees(1);
    }, 300);
});

positionFilter.addEventListener("change", function () {
    loadEmployees(1);
});
