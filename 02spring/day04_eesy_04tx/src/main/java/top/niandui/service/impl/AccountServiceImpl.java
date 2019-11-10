package top.niandui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.niandui.dao.IAccountDao;
import top.niandui.domain.Account;
import top.niandui.service.IAccountService;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer..........");
        // 1. 根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        // 2. 根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        // 3. 转出账户减钱
        source.setMoney(source.getMoney() - money);
        // 4. 转入账户加钱
        target.setMoney(target.getMoney() + money);
        // 5. 更新转出账户
        accountDao.updateAccount(source);

//        int i = 1 / 0;

        // 6. 更新转入账户
        accountDao.updateAccount(target);
    }
}
