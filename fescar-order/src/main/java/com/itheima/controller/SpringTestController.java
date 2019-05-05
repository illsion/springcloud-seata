package com.itheima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class SpringTestController {
    @Autowired
    private OrderInfoServiceImpl orderInfoServiceImpl;

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String findById() {
        try {
            orderInfoServiceImpl.create("itheima",1, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
