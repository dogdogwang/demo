package net.wjd.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    /**
     * ���л�
     * @param object
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // ���л�
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
     * �����л�
     * @param bytes
     */
    public static Object unserialize(byte[] bytes) {
    	
    	if(bytes==null)
    		return null;
    	
        ByteArrayInputStream bais = null;
        try {
            // �����л�
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
		g1.setName("ƻ��");
		g1.setPrice(5f);
		g1.setDesc("�����ƻ��������");
		
		Goods g2 = new Goods();
		g2.setName("����");
		g2.setPrice(3.5f);
		g2.setDesc("���������ˮ�ܶ�");
		
		RedisUtil.getJedis().set("g1".getBytes(), SerializeUtil.serialize(g1));
		byte[] bg1 = RedisUtil.getJedis().get("g1".getBytes());
		Goods rg1 = (Goods)SerializeUtil.unserialize(bg1);
		System.out.println(rg1.getName());
		System.out.println(rg1.getPrice());
		System.out.println(rg1.getDesc());
	}
}
