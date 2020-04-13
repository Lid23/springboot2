package com.noodles.security.utils;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * @filename RSAUtils
 * @description RSA加解密工具类
 * @author noodles
 * @date 2019/3/26 16:51
 */
public class RsaUtils {

	/**
	 * 私有构造函数
	 * @return
	 * @author noodles
	 * @date 2019/8/6 14:54
	 */
	private RsaUtils() {

	}

	/**
	 *  RSA公钥加密
	 * @param str  加密字符串
	 * @param publicKey 公钥
	 * @return java.lang.String 密文
	 * @author noodles
	 * @date 2019/6/25 17:52
	 */
	public static String encrypt(String str, String publicKey) throws Exception {
		//base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(decoded));
		//RSA加密
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] data = str.getBytes("UTF-8");
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		for (int i = 0; inputLen - offSet > 0; offSet = i * 117) {
			byte[] cache;
			if (inputLen - offSet > 117) {
				cache = cipher.doFinal(data, offSet, 117);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}
		return Base64.encodeBase64String(out.toByteArray());
	}

	/**
	 * RSA私钥解密
	 * @param str 加密字符串
	 * @param privateKey  私钥
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/6/25 17:53
	 */
	public static String decrypt(String str, String privateKey) throws Exception {
		//64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
		//base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		//RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		int inputLen = inputByte.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		for (int i = 0; inputLen - offSet > 0; offSet = i * 128) {
			byte[] cache;
			if (inputLen - offSet > 128) {
				cache = cipher.doFinal(inputByte, offSet, 128);
			} else {
				cache = cipher.doFinal(inputByte, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}
		byte[] decryptedData = out.toByteArray();
		return new String(decryptedData, "UTF-8");
	}

	/**
	 * 使用SHA1withRSA签名算法产生签名
	 * @param src 签名的原字符串
	 * @param priKey 签名时使用的私钥(16进制编码)
	 * @return byte[]  签名的返回结果(16进制编码)
	 * @author noodles
	 * @date 2019/6/25 17:50
	 */
	public static byte[] generateSHA1withRSASigature(String src, String priKey) {
		try {
			Signature sigEng = Signature.getInstance("SHA1withRSA");
			byte[] pribyte = Base64Utils.decode(priKey);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pribyte);
			KeyFactory fac = KeyFactory.getInstance("RSA");
			RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
			sigEng.initSign(privateKey);
			sigEng.update(src.getBytes());
			byte[] signature = sigEng.sign();
			return signature;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能描述	签名比对
	 * @param str    签名字符串
	 * @param partnerPubKey 合作方公钥
	 * @return boolean
	 * @author Leven
	 * @date 2019/6/13 15:01
	 */
	public static boolean checkSigature(String str, String sign, String partnerPubKey) {
		try {
			Signature verifySign = Signature.getInstance("SHA1withRSA");
			byte[] pubKeyByte = Base64Utils.decode(partnerPubKey);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubKeyByte);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			verifySign.initVerify(publicKey);
			//用于验签的数据
			verifySign.update(str.getBytes("UTF-8"));
			boolean flag = verifySign.verify(Base64Utils.decode(sign));
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 功能描述 生产RSA密钥对
	 * @param
	 * @return java.util.Map
	 * @author Leven
	 * @date 2019/6/14 10:25
	 */
	public static Map<String, String> createKeys() {
		//为RSA算法创建一个KeyPairGenerator对象
		KeyPairGenerator keyPairGenerator = null;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			//初始化KeyPairGenerator对象,密钥长度默认1024
			keyPairGenerator.initialize(1024);
			//生成密匙对
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			//得到公钥
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			//得到私钥
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, String> map = new HashMap<>();
			map.put("pubKey", Base64Utils.encode(rsaPublicKey.getEncoded()));
			map.put("priKey", Base64Utils.encode(rsaPrivateKey.getEncoded()));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}