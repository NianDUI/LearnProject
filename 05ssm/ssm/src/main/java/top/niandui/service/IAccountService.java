package top.niandui.service;

import top.niandui.domain.Account;

import java.util.List;

/**
 * 账户业务成接口
 */
public interface IAccountService {

    // 查询所有账户信息
    public List<Account> findAll();

    // 保存账户信息
    public void saveAccount(Account account);
}
