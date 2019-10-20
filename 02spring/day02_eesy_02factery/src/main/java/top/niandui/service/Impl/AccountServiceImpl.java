package top.niandui.service.Impl;

import top.niandui.dao.IAccountDao;
import top.niandui.dao.impl.AccountDao;
import top.niandui.service.IAccountService;

/**
 * 账户的业务实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao = new AccountDao();

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
