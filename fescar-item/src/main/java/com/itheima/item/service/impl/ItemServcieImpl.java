package com.itheima.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fescar.core.context.RootContext;
import com.itheima.item.service.AccountServiceController;
import com.itheima.mapper.ItemMapper;
import com.itheima.model.Account;
import com.itheima.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2019/3/30 17:23
 *
 ****/
@RestController
@RequestMapping(value = "/item")
public class ItemServcieImpl {


    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private AccountServiceController accountServiceController;


    @RequestMapping(value = "/test")
    public String sds(){
        return "successful";
    }

    /****
     * 修改商品库存，同时修改账户余额
     * @param item
     * @param account
     * @return
     */
    @RequestMapping(value = "/update")
    public int update(@RequestBody Item item, @RequestParam("account") String account) {
        System.out.println("方法中xid:"+ RootContext.getXID());
        //修改商品的库存
        int mcount = itemMapper.update(item);
        System.out.println("更新库存受影响行数：" + mcount);
        Account account1 = JSON.parseObject(account, Account.class);
        //修改账户余额
//        int q = 10 / 0;
        int acount = accountServiceController.update(account1);
        System.out.println("账户更新余额受影响行数:" + acount);
        return mcount;
    }


}
