package com.itheima.sercice.impl;

import com.alibaba.fescar.core.context.RootContext;
import com.itheima.mapper.AccountMapper;
import com.itheima.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class Controller {
    @Autowired
    private AccountMapper accountMapper;
    @RequestMapping(value = "/update")
    public int update(@RequestBody Account account){
        System.out.println("方法中xid:"+ RootContext.getXID());
        int update = accountMapper.update(account);
//        System.out.println("异常了...");
//        int q = 10/0;
        return update;
    }
}
