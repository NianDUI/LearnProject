package top.niandui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.niandui.dao.IAccountDao;
import top.niandui.domain.Account;
import top.niandui.service.IAccountService;

import java.util.List;

/**
 * 账户业务层实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("业务层：查询所有账户");

//        return null;
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：保存账户");
        accountDao.saveAccount(account);
    }
}
