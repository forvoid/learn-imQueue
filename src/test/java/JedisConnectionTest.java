import com.forvoid.util.JedisUtile;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by forvoid on 2017/8/2.
 */
public class JedisConnectionTest {
    public static void main(String[] args) {
        Jedis jedis = JedisUtile.getJedis();
        System.out.println(jedis);
        System.out.println("连接成功");
//        System.out.println(jedis.ping());
        //单个字符
//        jedis.set("xingming","陶翔");
//        System.out.println(jedis.get("xingming"));

        //数组
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }

        //获取key值
        // 获取数据并输出
        Set<String> keys = jedis.keys("lu");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
        JedisUtile.returnResource(jedis);
    }
}
