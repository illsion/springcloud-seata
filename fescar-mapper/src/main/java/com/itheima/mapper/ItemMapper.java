package com.itheima.mapper;

import com.itheima.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2019/3/30 16:58
 *
 ****/
//@Mapper
public interface ItemMapper {

    /***
     * 修改库存数量
     * @param item
     * @return
     */
    @Update("update item set num=num-#{num} where id=#{id}")
    int update(Item item);
}
