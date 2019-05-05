package com.itheima.model;

import java.io.Serializable;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2019/3/30 16:54
 *
 ****/
public class Account implements Serializable {
    private String usernumber;
    private Long money;
    private String username;

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
