
package net.jerryfu.decodeAndEncode;

import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5签名工具类
 * @author jerryfu
 *
 */
public class MD5Util {

    private static final Logger LOG = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private MD5Util() {
    }

    private static final String[] hex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 32位MD5签名值
     * @param password
     * @return
     */
    public static String encode32(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = md5.digest(password.getBytes("utf-8"));
            String passwordMD5 = byteArrayToHexString(byteArray);
            return passwordMD5;
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return password;
    }

    /**
     * 32位大写MD5签名值
     * @param password
     * @return
     */
    public static String encode32ToUpperCase(String password) {
        return encode32(password).toUpperCase();
    }

    /**
     * 16位MD5签名值
     * @param password
     * @return
     */
    public static String encode16(String password) {
        return encode32(password).substring(8, 24);
    }

    /**
     * 16位大写MD5签名值
     * @param password
     * @return
     */
    public static String encode16ToUpperCase(String password) {
        return encode32ToUpperCase(password).substring(8,24);
    }

    public static String encode(String password , String enc) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = md5.digest(password.getBytes(enc));
            String passwordMD5 = byteArrayToHexString(byteArray);
            return passwordMD5;
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return password;
    }

    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (byte b : byteArray) {
            sb.append(byteToHexChar(b));
        }
        return sb.toString();
    }

    private static Object byteToHexChar(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hex[d1] + hex[d2];
    }

    public static void main(String [] args ){

        String ss = "test";
        System.out.println(MD5Util.encode32(ss));
        System.out.println(MD5Util.encode32ToUpperCase(ss));
        System.out.println(MD5Util.encode16(ss));
        System.out.println(MD5Util.encode16ToUpperCase(ss));
    }
    
  

}
