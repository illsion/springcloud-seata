package com.itheima.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.itheima.mapper.OrderInfoMapper;
import com.itheima.model.Account;
import com.itheima.model.Item;
import com.itheima.model.OrderInfo;
import com.itheima.service.ItemServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

@Component
public class OrderInfoServiceImpl {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private ItemServiceController itemServiceController;



  /*  public void init(){
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new RequestContextHystrixConcurrencyStrategy());
    }*/
    /****
     * 创建订单
     * 调用ItemService修改库存(调用AccountService修改余额)
     *
     * @param  usernumber:购买商品的用户
     * @param  id：购买的商品ID
     * @param  count:垢面的数量
     */
    @GlobalTransactional(name = "fescar-itheima-tx")
    public int create(String usernumber, Integer id, Integer count) throws  Exception{
        System.out.println("controller中xid为:"+RootContext.getXID());
        RequestContextHolder.getRequestAttributes().setAttribute("xid",RootContext.getXID(),SCOPE_SESSION);
        System.out.println("当前线程对象:"+Thread.currentThread());
        System.out.println("当前线程对比名字:"+  RequestContextHolder.getRequestAttributes());
        int acount = 0;
            //从数据库查询商品信息
            Item item = new Item();
            item.setId(id);
            item.setNum(count);
            item.setPrice(100L);
            item.setTitle("华为荣耀4");

            //创建订单
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId((int) (Math.random() * 1000));
            orderInfo.setMoney(item.getPrice() * count);
            orderInfo.setCreatetime(new Date());
            orderInfo.setUsernumber(usernumber);
            acount = orderInfoMapper.add(orderInfo);

            System.out.println("添加订单受影响行数：" + acount);
            //调用ItemService(远程调用)
            Account account = new Account();
            account.setUsernumber(usernumber);
            account.setMoney(item.getPrice() * count); //花掉的钱
            String account1 = JSON.toJSONString(account);
            Thread.sleep(3000);
            itemServiceController.update(item, account1);
        //制造异常
//        System.out.println("开始报错了。。。。");
//        int q=10/0;
        return acount;
    }


}
