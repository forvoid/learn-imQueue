package com.forvoid.JobTask;

import com.forvoid.bo.OrderFlow;
import com.forvoid.dao.OrderFlowDao;
import com.forvoid.mapper2.OrderFlowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by forvoid on 2017/8/2.
 */
public class OrderFlowJob  {
    private static Logger logger = LoggerFactory.getLogger(OrderFlowJob.class);

    @Resource
    private OrderFlowDao orderFlowDao;

    @Resource
    private OrderFlowMapper orderFlowMapper;

    public void execute() {
        // 进行读取redis 操作并且保存到数据库中
        logger.debug("job start .......");
        List<OrderFlow> orderFlowList = orderFlowDao.getOrderFlow();
        if (orderFlowList == null || orderFlowList.size() == 0) {
            logger.info("没有数据可以使用");
        }else {
            for (OrderFlow orderFlow : orderFlowList) {
                orderFlowMapper.insertOrderFlow(orderFlow);
            }
        }
        logger.debug("job end .......");
    }
}
