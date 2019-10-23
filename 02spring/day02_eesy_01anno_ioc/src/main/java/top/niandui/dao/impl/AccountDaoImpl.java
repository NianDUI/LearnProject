package top.niandui.dao.impl;

import org.springframework.stereotype.Repository;
import top.niandui.dao.IAccountDao;

/**
 * 账户的持久层实现类
 */
@Repository("accountDao1")
public class AccountDaoImpl implements IAccountDao {

    public void saveAccount() {
        System.out.println("保存了账户11111111");
    }


}
