package net.wjd.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    /**
     * 序列化
     * @param object
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     * @param bytes
     */
    public static Object unserialize(byte[] bytes) {
    	
    	if(bytes==null)
    		return null;
    	
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    
    
    public static void main (String[] args) {
		Goods g1 = new Goods();
		g1.setName("苹果");
		g1.setPrice(5f);
		g1.setDesc("这里的苹果大又甜");
		
		Goods g2 = new Goods();
		g2.setName("橘子");
		g2.setPrice(3.5f);
		g2.setDesc("这里的橘子水很多");
		
		RedisUtil.getJedis().set("g1".getBytes(), SerializeUtil.serialize(g1));
		byte[] bg1 = RedisUtil.getJedis().get("g1".getBytes());
		Goods rg1 = (Goods)SerializeUtil.unserialize(bg1);
		System.out.println(rg1.getName());
		System.out.println(rg1.getPrice());
		System.out.println(rg1.getDesc());
	}
}
