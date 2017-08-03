import com.forvoid.mapper.OrderMapper;
import com.forvoid.mapper2.OrderFlowMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by forvoid on 2017/8/2.
 */
public class DoubleDataSourceTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application.xml");

        OrderMapper orderMapper = applicationContext.getBean(OrderMapper.class);
        System.out.println(orderMapper.select().toString());
        OrderFlowMapper orderFlowMapper = applicationContext.getBean(OrderFlowMapper.class);
        System.out.println(orderFlowMapper.selectById().toString());
    }
}
