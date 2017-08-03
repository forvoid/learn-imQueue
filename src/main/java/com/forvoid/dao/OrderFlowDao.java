package com.forvoid.dao;

import com.forvoid.bo.OrderFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/**
 * Created by forvoid on 2017/8/2.
 * 当错误为RedisSystemException才会进行回滚
 * throw new RedisSystemException("dsd", new Exception("myself exception....."));
 */
@Service
@Transactional
public class OrderFlowDao {
    public static Logger logger = LoggerFactory.getLogger(OrderFlowDao.class);
    private final static String LIST_NAME = "orderFlow";
    @Resource
    private RedisTemplate<Serializable, OrderFlow> redisTemplate;
    @Resource(name = "redisTemplate")
    private ListOperations<String, OrderFlow> listOps;


    public void saveOrderFlow(OrderFlow orderFlow) {
        listOps.leftPush(LIST_NAME, orderFlow);
        logger.info("saveOrderFlow list success");

    }
    public List<OrderFlow> getOrderFlow() {
        List list = listOps.range(LIST_NAME, 0 , -1);
        redisTemplate.delete(LIST_NAME);
        logger.info("getOrderFile list success");
        return list;

    }

    public static byte[] getOOS(Object object) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {

            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            byte[] byteArray = bos.toByteArray();
            return byteArray;
        } catch (IOException e) {
            logger.error("orderFlow to object Serializable error ,Now return null");
        }
        return null;
    }

    public static Object getObject(byte[] bs) {
        try {

            ByteArrayInputStream bis = new ByteArrayInputStream(bs);
            ObjectInputStream ois = new ObjectInputStream(bis);

            return ois.readObject();

        } catch (IOException e) {
            logger.error("object Serializable to Object error, Now return null",e);
        } catch (ClassNotFoundException c) {
            logger.error("not get the object",c);
        }
        return null;
    }


}
