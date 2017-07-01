package net.wjd.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestRedis {
	private Jedis jedis; 
    
    @Before
    public void setup() {
        //����redis������
        jedis = new Jedis("127.0.0.1", 6379);
    }
    
    /**
     * redis�洢�ַ���
     */
    @Test
    public void testString() {
        //-----�������----------  
        jedis.set("name","yuanxj");//��key-->name�з�����value-->yuanxj  
        System.out.println(jedis.get("name"));//ִ�н����yuanxj  
        
        jedis.append("name", " is Fraud"); //ƴ��
        System.out.println(jedis.get("name")); 
        
        jedis.del("name");  //ɾ��ĳ����
        System.out.println(jedis.get("name"));
        //���ö����ֵ��
        jedis.mset("name","Viking","age","30","qq","1001");
        jedis.incr("age"); //���м�1����
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
        
    }
    
    /**
     * redis����Map
     */
    @Test
    public void testMap() {
        //-----�������----------  
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "yuanxj");
        map.put("age", "30");
        map.put("qq", "1001");
        jedis.hmset("user1",map);
        //��һ�������Ǵ���redis��map�����key����������Ƿ���map�еĶ����key�������key���Ը�������ǿɱ����  
        List<String> rsmap = jedis.hmget("user1", "name", "age", "qq");
        System.out.println(rsmap);  
  
        //ɾ��map�е�ĳ����ֵ  
        jedis.hdel("user1","age");
        System.out.println(jedis.hmget("user1", "age")); //��Ϊɾ���ˣ����Է��ص���null  
        System.out.println(jedis.hlen("user1")); //����keyΪuser�ļ��д�ŵ�ֵ�ĸ���2 
        System.out.println(jedis.exists("user1"));//�Ƿ����keyΪuser�ļ�¼ ����true  
        System.out.println(jedis.hkeys("user1"));//����map�����е�����key  
        System.out.println(jedis.hvals("user1"));//����map�����е�����value 
  
        Iterator<String> iter=jedis.hkeys("user1").iterator();  
        while (iter.hasNext()){  
            String key = iter.next();  
            System.out.println(key+":"+jedis.hmget("user1",key));  
        }  
    }
    
    /** 
     * jedis����List 
     */  
    @Test  
    public void testList(){  
        //��ʼǰ�����Ƴ����е�����  
        jedis.del("java framework");  
        System.out.println(jedis.lrange("java framework",0,-1));  
        //����key java framework�д����������  
        jedis.lpush("java framework","spring");  
        jedis.lpush("java framework","struts");  
        jedis.lpush("java framework","hibernate");  
        //��ȡ����������jedis.lrange�ǰ���Χȡ����  
        // ��һ����key���ڶ�������ʼλ�ã��������ǽ���λ�ã�jedis.llen��ȡ���� -1��ʾȡ������  
        System.out.println(jedis.lrange("java framework",0,-1));  
        
        jedis.del("java framework");
        jedis.rpush("java framework","spring");  
        jedis.rpush("java framework","struts");  
        jedis.rpush("java framework","hibernate"); 
        System.out.println(jedis.lrange("java framework",0,-1));
    }  
    
    /** 
     * jedis����Set 
     */  
    @Test  
    public void testSet(){  
        //���  
        jedis.sadd("user2","yuanxj");  
        jedis.sadd("user2","Fraud");  
        jedis.sadd("user2","Viking");  
        jedis.sadd("user2","Jake");
        jedis.sadd("user2","who");  
        //�Ƴ�noname  
        jedis.srem("user2","who");  
        System.out.println(jedis.smembers("user2"));//��ȡ���м����value  
        System.out.println(jedis.sismember("user2", "who"));//�ж� who �Ƿ���user���ϵ�Ԫ��  
        System.out.println(jedis.srandmember("user2"));  
        System.out.println(jedis.scard("user2"));//���ؼ��ϵ�Ԫ�ظ���  
    }  
  
    @Test  
    public void test() throws InterruptedException {  
        //jedis ����  
        //ע�⣬�˴���rpush��lpush��List�Ĳ�������һ��˫���������ӱ��������ģ�  
        jedis.del("a");//��������ݣ��ټ������ݽ��в���  
        jedis.rpush("a", "1");  
        jedis.lpush("a","6");  
        jedis.lpush("a","3");  
        jedis.lpush("a","9");  
        System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]  
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //�����������  
        System.out.println(jedis.lrange("a",0,-1));  
    }  
    
    @Test
    public void testRedisPool() {
        RedisUtil.getJedis().set("newname", "���Ĳ���");
        System.out.println(RedisUtil.getJedis().get("newname"));
    }
}
