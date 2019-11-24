package top.niandui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.niandui.domain.Account;
import top.niandui.service.IAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户web层
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    /**
     * 查询所有的账户信息
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        System.out.println("表现层：查询所有的账户信息");

        // 调用service方法
        List<Account> list = accountService.findAll();
        model.addAttribute("list", list);

        return "list";
    }

    /**
     * 保存账户信息
     * @param account
     * @return
     */
    @RequestMapping("/saveAccount")
    public void saveAccount(Account account, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("表现层：保存账户信息");

        // 调用service方法
        accountService.saveAccount(account);

        response.sendRedirect(request.getContextPath() + "/account/findAll");
    }
}
