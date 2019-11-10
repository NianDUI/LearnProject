package top.niandui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.niandui.dao.IAccountDao;
import top.niandui.domain.Account;
import top.niandui.service.IAccountService;

import java.util.List;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)  // 只读型事务的配置
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    // 需要读写型事务的配置
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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

        int i = 1 / 0;

        // 6. 更新转入账户
        accountDao.updateAccount(target);
    }
}
