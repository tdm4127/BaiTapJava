// Thu gọn hoặc mở rộng thanh menu bên trái.
const sidebarToggle = document.getElementById("sidebarToggle");
const appLayout = document.getElementById("appLayout");

if (sidebarToggle && appLayout) {
    sidebarToggle.addEventListener("click", function () {
        appLayout.classList.toggle("sidebar-collapsed");
    });
}

// Đánh dấu mục menu tương ứng với trang hiện tại.
document.querySelectorAll(".sidebar a").forEach(function (link) {
    if (link.pathname === window.location.pathname) {
        link.classList.add("active");
    }
});
