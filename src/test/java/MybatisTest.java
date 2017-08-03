import com.forvoid.mapper2.OrderFlowMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by forvoid on 2017/8/2.
 */
public class MybatisTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application.xml");
        OrderFlowMapper orderMapper = applicationContext.getBean(OrderFlowMapper.class);
//        Order order = new Order();
//        order.setStartTime(new Date());
//        order.setCode("code");
//        order.setName("name");
//        orderMapper.saveOrder(order);
        System.out.println(orderMapper.selectById().toString());
//        System.out.println(orderMapper.select());
    }
}
