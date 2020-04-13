package com.noodles.security.utils;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @filename DESUtils
 * @description DES加解密工具类
 * @author noodles
 * @date 2019/3/26 16:51
 */
public class DesUtils {

	/**解密方式*/
	private final static String DES = "DES";
	/**编码格式*/
	private final static String ENCODE = "UTF-8";
	/**默认秘钥*/
	private final static String defaultKey = "6319dd2d032d503a92491398";

	/**
	 * 私有构造函数
	 * @return
	 * @author noodles
	 * @date 2019/8/6 14:53
	 */
	private DesUtils() {

	}

	/**
	 * 使用默认key加密
	 * @param data 待加密数据
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/6/25 17:56
	 */
	public static String encrypt(String data) throws Exception {
		byte[] bt = encrypt(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
		String strs = Base64Utils.encode(bt);
		return strs;
	}

	/**
	 * 使用默认key解密
	 * @param data 待解密数据
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/6/25 18:03
	 */
	public static String decrypt(String data) throws IOException, Exception {
		if (data == null)
			return null;
		byte[] buf = Base64Utils.decode(data);
		byte[] bt = decrypt(buf, defaultKey.getBytes(ENCODE));
		return new String(bt, ENCODE);
	}

	/**
	 * 根据键值进行加密
	 * @param data 待加密数据
	 * @param key 秘钥
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/6/25 18:05
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}

	/**
	 * 根据键值进行解密
	 * @param data 待解密数据
	 * @param key 秘钥
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/6/25 18:05
	 */
	public static String decrypt(String data, String key) throws IOException, Exception {
		if (data == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = decoder.decodeBuffer(data);
		byte[] bt = decrypt(buf, key.getBytes(ENCODE));
		return new String(bt, ENCODE);
	}

	/**
	 * 根据键值进行加密
	 * @param data 待加密数据
	 * @param key 秘钥
	 * @return byte[]
	 * @author noodles
	 * @date 2019/6/25 18:05
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}

	/**
	 * 根据键值进行解密
	 * @param data 待加密数据
	 * @param key 秘钥
	 * @return byte[]
	 * @author noodles
	 * @date 2019/6/25 18:07
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}
}
