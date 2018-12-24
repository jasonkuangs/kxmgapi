package com.sy.basic.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileMd5 {
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
	    'f' };
    protected static MessageDigest messagedigest = null;

    static {
	try {
	    messagedigest = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
    }

    /**
     * yhl
     * 2015年7月10日 上午11:39:14
     * @描述 计算文件MD5码 注意synchronized！！！
     * @param inputStream 输入留
     * @return
     * @throws IOException
     */
    public static synchronized String getFileMD5String(InputStream inputStream) throws IOException {
	InputStream fis = inputStream;

	byte[] buffer = new byte[1024];
	int numRead = 0;
	while ((numRead = fis.read(buffer)) > 0) {
	    messagedigest.update(buffer, 0, numRead);
	}
	fis.close();
	return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
	return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
	StringBuffer stringbuffer = new StringBuffer(2 * n);
	int k = m + n;
	for (int l = m; l < k; l++) {
	    appendHexPair(bytes[l], stringbuffer);
	}
	return stringbuffer.toString().toUpperCase();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
	char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换
	// 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
	char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
	stringbuffer.append(c0);
	stringbuffer.append(c1);
    }
}
