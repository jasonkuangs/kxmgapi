package com.sy.basic.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * 数据安全（加密解密）
 * @author 高垒
 * @date 2013-7-24 下午05:40:30
 */
public class DataSafetyUtil {

    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * koler@gao
     */
    private static final byte[] KEY = { 6, 118, 99, -31, -11, 16, 23, 28, -77, 25, 81, 17, 33, -50, -23, 59 };

    /**
     * @param key
     * @return
     * @throws Exception
     */
    public static Key getKey(String key) throws Exception {
	KeyGenerator kgen = KeyGenerator.getInstance("AES");
	kgen.init(128, new SecureRandom(key.getBytes()));
	return kgen.generateKey();
    }

    /**
     * 得到默认密钥
     * @return
     */
    public static Key getKey() {
	return new SecretKeySpec(KEY, KEY_ALGORITHM);
    }

    /**
     * @param originalData 原始未加密数据
     * @return 加密的数据
     * @throws Exception
     */
    public static String getSafeData(String originalData, Key key) throws Exception {

	return new String(byteToChar(safeData(originalData, key)));
    }

    /**
     * @param safeData 已加密的数据
     * @return 解密数据
     */
    public static String getOriginalData(String safeData, Key key) throws Exception {
	return getOriginalData(charToByte(safeData.toCharArray()), key);
    }

    /**
     * @param safeData
     * @param key
     * @return
     * @throws Exception
     */
    public static String getOriginalData(byte[] safeData, Key key) throws Exception {
	Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
	cipher.init(Cipher.DECRYPT_MODE, key);
	return new String(cipher.doFinal(safeData));
    }

    /**
     * @param originalData
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] safeData(String originalData, Key key) throws Exception {

	Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
	cipher.init(Cipher.ENCRYPT_MODE, key);
	byte[] by = originalData.getBytes();
	return cipher.doFinal(by);
    }

    /**
     * @param by
     * @return
     */
    public static char[] byteToChar(byte[] bys) {
	char[] result = new char[bys.length];
	for (int i = 0; i < bys.length; i++) {
	    result[i] = (char) (bys[i]);
	}
	return result;
    }

    /**
     * @param chs
     * @return
     */
    public static byte[] charToByte(char[] chs) {
	byte[] result = new byte[chs.length];
	for (int i = 0; i < chs.length; i++) {
	    result[i] = (byte) (chs[i]);
	}
	return result;
    }

    public static void main(String[] args) {
	String s = "fdsfdasfda";
	try {
	    Key key = getKey("koler@gao");
	    //System.out.println(Arrays.toString(key.getEncoded()));
	    //System.out.println(getSafeData(s, getKey()));
	    //System.out.println(getOriginalData(getSafeData(s, getKey()), getKey()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
