package com.forvoid.mapper2;

import com.forvoid.bo.OrderFlow;

/**
 * Created by forvoid on 2017/8/2.
 */
public interface OrderFlowMapper {
    OrderFlow selectById();
    int insertOrderFlow(OrderFlow orderFlow);
}
