import com.forvoid.bo.OrderFlow;
import com.forvoid.dao.OrderFlowDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by forvoid on 2017/8/2.
 */
public class RedisAndSpringTest {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application.xml");

        OrderFlowDao orderFlowDao = (OrderFlowDao) applicationContext.getBean("orderFlowDao");

        OrderFlow orderFlow = new OrderFlow();
        orderFlow.setOfid(123);
        orderFlow.setCreateTime(new Date().getTime());

        orderFlowDao.saveOrderFlow(orderFlow);
        orderFlow.setOfid(122223);
        Thread.sleep(1000);
        orderFlow.setCreateTime(new Date().getTime());
        orderFlowDao.saveOrderFlow(orderFlow);
        orderFlowDao.saveOrderFlow(orderFlow);
        orderFlowDao.saveOrderFlow(orderFlow);
        orderFlowDao.saveOrderFlow(orderFlow);

        for (OrderFlow orderFlow1 : orderFlowDao.getOrderFlow()) {
            System.out.println(orderFlow1.toString());
        }
//        orderFlowDao.delete(123);
//        System.out.println(orderFlowDao.read(123).toString());
    }
}
