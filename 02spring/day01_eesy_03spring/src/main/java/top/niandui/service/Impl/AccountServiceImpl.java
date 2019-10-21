package top.niandui.service.Impl;

import top.niandui.dao.IAccountDao;
import top.niandui.dao.impl.AccountDaoImpl;
import top.niandui.service.IAccountService;

/**
 * 账户的业务实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao = new AccountDaoImpl();

    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }

}
