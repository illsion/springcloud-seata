package com.itheima.service;

import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.itheima.hystrix.TestHystrix;
import com.itheima.model.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "item",fallback = TestHystrix.class)
public interface ItemServiceController {
    @RequestMapping(value = "/item/update")

    int update( @RequestBody Item item, @RequestParam("account") String account);
}
