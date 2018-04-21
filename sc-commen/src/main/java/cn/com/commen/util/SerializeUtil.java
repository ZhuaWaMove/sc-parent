package cn.com.commen.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 *<p>Title:SerializeUtil</p>
 *<p>Description:redis 序列化对象类</p>
 *<p>Company:南天信息工程</p>
 * @author Acmen
 * @date 2018年2月28日 上午11:07:19
 *
 */
public class SerializeUtil {
	
	/**
	 * 
	 *<p>Title:serialize</p>
	 *<p>Description:序列化对象类</p>
	 *<p>param @param obj
	 *<p>param @return</p>
	 *<p>return byte[]</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年2月28日 上午11:11:05
	 *
	 */
	public static byte[] serialize(Object obj) {
		ByteArrayOutputStream bos = null; //字节输出流
		ObjectOutputStream oos = null;  
		try {
			//将对象转换成字节流
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			byte[] byteArray = bos.toByteArray();
			return byteArray;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 *<p>Title:unserialize</p>
	 *<p>Description:反序列化對象</p>
	 *<p>param @param byteArray
	 *<p>param @return</p>
	 *<p>return Object</p>
	 *<p>Company:南天信息工程</p>
	 * @author Acmen
	 * @date 2018年2月28日 上午11:18:27
	 *
	 */
	public static Object unserialize(byte [] byteArray) {
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			//将字节转换成对象
			bis = new ByteArrayInputStream(byteArray);
			ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
