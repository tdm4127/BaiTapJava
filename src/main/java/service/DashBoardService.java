// Service Dashboard: lấy số liệu từ DAO.
package service;

import dao.DashBoardDao;

public class DashBoardService {

    private DashBoardDao dashboardDAO = new DashBoardDao();

    public int countEmployees() {
        return dashboardDAO.countEmployees();
    }

    public int countProjects() {
        return dashboardDAO.countProjects();
    }

    public int countTasks() {
        return dashboardDAO.countTasks();
    }

}
