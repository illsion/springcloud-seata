package com.itheima.hystrix;

import com.itheima.service.ItemServiceController;
import com.itheima.model.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

//记得被spring管理
@Component
public class TestHystrix implements ItemServiceController {
    @Override
    public int update(Item item, String account) {

        System.out.println("启动了熔断器");
        return 0;
    }
}
