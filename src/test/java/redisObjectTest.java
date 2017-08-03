import com.forvoid.bo.OrderFlow;
import com.forvoid.util.JedisUtile;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.Date;

/**
 * Created by forvoid on 2017/8/2.
 */
public class redisObjectTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        saveTest();
        delTest();
        getTest();
    }
    public static void saveTest() throws IOException {
        Jedis jedis = JedisUtile.getJedis();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        OrderFlow orderFlow = new OrderFlow();
        orderFlow.setCreateTime(new Date().getTime());
        orderFlow.setStatus(1);

        oos.writeObject(orderFlow);

        byte[] byteArray = bos.toByteArray();
        oos.close();
        bos.close();
        String setObjectRet = jedis.set("mingyuan".getBytes(), byteArray);
        System.out.println(" set object return \t" + setObjectRet);
    }
    public static void delTest() {
        Jedis jedis = JedisUtile.getJedis();
        jedis.del("mingyuan".getBytes());
    }
    public static void getTest() throws IOException, ClassNotFoundException {
        Jedis jedis = JedisUtile.getJedis();
        byte[] bs = jedis.get("mingyuan".getBytes());

        ByteArrayInputStream bis = new ByteArrayInputStream(bs);
        ObjectInputStream ois = new ObjectInputStream(bis);

        OrderFlow orderFlow = (OrderFlow) ois.readObject();

        System.out.println(orderFlow.toString());


    }
}
