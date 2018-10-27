package com.eysale.zonelee.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

    public static String encryptionByMD5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(msg.getBytes());
            String s = toHex(bytes);
            LogPrinter.i("EncryptionUtil", "encryptionByMD5 : " + s);
            return s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return msg;
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

}
