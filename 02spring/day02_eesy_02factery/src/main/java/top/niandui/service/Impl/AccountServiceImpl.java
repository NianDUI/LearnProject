package top.niandui.service.Impl;

import top.niandui.dao.IAccountDao;
import top.niandui.dao.impl.AccountDaoImpl;
import top.niandui.factory.BeanFactory;
import top.niandui.service.IAccountService;

/**
 * 账户的业务实现类
 */
public class AccountServiceImpl implements IAccountService {

//    private IAccountDao accountDao = new AccountDaoImpl();
    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

//    private int i = 1;

    public void saveAccount() {
        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
