package com.dx.xjd.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class EncryptUtils {

	private static Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

	public static final String KEY_MD5 = "MD5";
	public static final String KEY_HMD5_256 = "HmacSHA256";

	//十六进制下数字到字符的映射数组
	private final static String[] hexDigits = {"0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


	/**
	 * 标准MD5加密
	 * @param content
	 * @return
	 */
	public static String encryptMD5_STD(String content) {
		String resultString = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(content.getBytes());

			// 将得到的字节数组变成字符串返回
			resultString = byteArrayToHexString(md5.digest());
		} catch (Exception e) {
			logger.error("encryptMD5 fail", e);
		}

		return resultString.toLowerCase();
	}

	/**
	 * HMac256加密
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptHMac256(String content, String key) {  
        
        String resultString = "";
		try {
			// 还原密钥  
	        SecretKey secretKey = new SecretKeySpec(key.getBytes(), KEY_HMD5_256);
			// 实例化Mac
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			// 初始化mac
			mac.init(secretKey);
			// 执行消息摘要
	        byte[] digest = mac.doFinal(content.getBytes());  
	        resultString = new String(Base64.encodeBase64(digest));
		} catch (Exception e) {
			logger.error("encryptHMac256 fail", e);
		}  
             
        return  resultString;
    }


	/**
	 * 转换字节数组为十六进制字符串
	 * @param b 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b){
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++){
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 将一个字节转化成十六进制形式的字符串
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b){
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}
