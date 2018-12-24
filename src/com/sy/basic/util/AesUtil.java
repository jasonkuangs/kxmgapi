package com.sy.basic.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
    private static final String UTF8 = "utf-8";
    // 定义 加密算法,可用 DES,DESede,Blowfish
    public static final String bm = "utf-8";
    public static final String PARAMS_KEY="8158356454126845";
    
    private static final String ALGORITHM_DESEDE = "DESede";
	private static final String ALGORITHM_AES = "AES";
	private static final String VIPARA = "1586323842684526";
	private static final String AESTYPE = "AES/CBC/PKCS5Padding";

    /**
     * MD5数字签名
     * @param src
     * @return
     * @throws Exception
     */
    public static String md5Digest(String src, int bit) throws Exception {
	// 定义数字签名方法, 可用：MD5, SHA-1
	MessageDigest md = MessageDigest.getInstance("MD5");
	byte[] b = md.digest(src.getBytes(UTF8));
	if (bit == 16) {
	    return byte2HexStr(b).substring(8, 24);
	} else {
	    return byte2HexStr(b);
	}
    }

    /**
     * BASE64 加密
     * @param src
     * @return
     * @throws Exception
     */
    /*
     * public static String base64Encoder(String src) throws Exception {
     * BASE64Encoder encoder = new BASE64Encoder(); return
     * encoder.encode(src.getBytes(UTF8)); }
     */

    /**
     * 3DES加密
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    public static String desedeEncoder(String src, String key) throws Exception {
	SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);
	Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
	cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	byte[] b = cipher.doFinal(src.getBytes(UTF8));

	return byte2HexStr(b);
    }

    /**
     * 3DES解密
     * @param dest
     * @param key
     * @return
     * @throws Exception
     */
    public static String desedeDecoder(String dest, String key) throws Exception {
	SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);
	Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
	cipher.init(Cipher.DECRYPT_MODE, secretKey);
	byte[] b = cipher.doFinal(str2ByteArray(dest));

	return new String(b, UTF8);

    }
    
    /**
	 * 将二进制转换成16进制
	 * @param buf
	 * @return
	 * @throws
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * @param hexStr
	 * @return
	 * @throws
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

    /**
     * 字节数组转化为大写16进制字符串
     * @param b
     * @return
     */
    private static String byte2HexStr(byte[] b) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < b.length; i++) {
	    String s = Integer.toHexString(b[i] & 0xFF);
	    if (s.length() == 1) {
		sb.append("0");
	    }

	    sb.append(s.toUpperCase());
	}

	return sb.toString();
    }

    /**
     * 字符串转字节数组
     * @param s
     * @return
     */
    private static byte[] str2ByteArray(String s) {
	int byteArrayLength = s.length() / 2;
	byte[] b = new byte[byteArrayLength];
	for (int i = 0; i < byteArrayLength; i++) {
	    byte b0 = (byte) Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16).intValue();
	    b[i] = b0;
	}

	return b;
    }

    /**
     * 构造3DES加解密方法key
     * @param keyStr
     * @return
     * @throws Exception
     */
    private static byte[] build3DesKey(String keyStr) throws Exception {
	byte[] key = new byte[24];
	byte[] temp = keyStr.getBytes(UTF8);
	if (key.length > temp.length) {
	    System.arraycopy(temp, 0, key, 0, temp.length);
	} else {
	    System.arraycopy(temp, 0, key, 0, key.length);
	}

	return key;
    }

    /**
     * AES 加密
     * @param content
     *            明文
     * @param password
     *            生成秘钥的关键字
     * @return
     */

    public static String aesEncrypt(String content, String password) {
    	try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), ALGORITHM_AES);
			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(content.getBytes(UTF8));
			// return Base64.encode(encryptedData);
			return parseByte2HexStr(encryptedData);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
    }

    /**
     * AES 解密
     * @param content
     *            密文
     * @param password
     *            生成秘钥的关键字
     * @return
     */

    public static String aesDecrypt(String content, String password) {
    	try {
			// byte[] byteMi = Base64.decode(content);
			byte[] byteMi = parseHexStr2Byte(content);
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), ALGORITHM_AES);
			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] decryptedData = cipher.doFinal(byteMi);
			return new String(decryptedData, UTF8);
		} catch (NoSuchAlgorithmException e) {
			//e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			//e.printStackTrace();
		} catch (InvalidKeyException e) {
			//e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			//e.printStackTrace();
		} catch (BadPaddingException e) {
			//e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			//e.printStackTrace();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
    }

    public static void main(String[] args) throws Exception {
	// Map map = new HashedMap();
	// map.put("mac", "1:1:k:jl1");
	// map.put("token",
	// "AD8DBE4144A67F69294D7A2E22E6F0E62C0542C492AB81E616514F9EDC9A2F779A68A4417719CA3F0F0BDF8AFA2D32D7");
	// map.put("first", "1");
	// map.put("scond", "2");
	// map.put("third", "0");
	// map.put("start", "0");
	// map.put("count", "10");
	// map.put("type", "0");
	// map.put("id", "11");
	// // "type", "type" 1, 2,0, 0, 10, 0, 27
	// String str = HttpUtils.createHttpParams(map, "1259632153695621", "com.amt.sign");
	// System.out.println(str);
	// // System.out.println(HttpUtils.createHttpParams(map, "1259632153695621", "6311365965521365"));
	// System.out.println(aesDecrypt(str, "1259632153695621"));
    }
}