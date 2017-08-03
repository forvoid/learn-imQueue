package com.forvoid.service;

import com.forvoid.bo.Order;
import com.forvoid.bo.OrderFlow;
import com.forvoid.dao.OrderFlowDao;
import com.forvoid.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
/**
 * Created by forvoid on 2017/8/3.
 */
@Service
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderFlowDao orderFlowDao;
    @Resource
    private OrderMapper orderMapper;

    /**
     *
     * 存储数据，进行订单的的直接下载，然后把订单流水表存储在redis。
     *      然后启用定时任务，在一定时间内进行对订单流水表取出 redis后进行数据插入其他的数据库
     * */
    public void addOrder(Order order) {
        if (orderMapper.saveOrder(order) <= 0){
            logger.error("order add error");
        }
        //创建订单流水表
        OrderFlow orderFlow = new OrderFlow();
        orderFlow.setOid(order.getOid());
        orderFlow.setStatus(0);
        orderFlow.setCreateTime(new Date().getTime());
        orderFlowDao.saveOrderFlow(orderFlow);

        logger.info("add success order information");
    }
}
