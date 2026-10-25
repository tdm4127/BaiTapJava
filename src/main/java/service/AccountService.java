// Service tài khoản: kiểm tra thông tin đăng nhập.
package service;

import dao.AccountDao;
import model.Account;

public class AccountService {

    private AccountDao accDao = new AccountDao();

    public Account login(String username, String password) {
        if (username != null && !username.trim().isEmpty()) {
            Account account = accDao.findAccount(username);
            if (account != null) {
                if (password.equals(account.getPassword())) {
                    return account;
                }
            }
        }
        return null;
    }
}
