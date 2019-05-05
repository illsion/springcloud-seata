package com.itheima.item.service;

import com.itheima.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account")
public interface AccountServiceController {

   @RequestMapping(value = "/account/update")
   int update(@RequestBody Account account);


}
