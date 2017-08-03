package com.forvoid.mapper;

import com.forvoid.bo.Order;
import org.apache.ibatis.annotations.Param;

/**
 * Created by forvoid on 2017/8/2.
 */
public interface OrderMapper {
    int saveOrder( Order order);
    Order select();
//    void saveOrder(Order order);
}
