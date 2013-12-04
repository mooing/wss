package com.mooing.wss.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5
 * @version $Id: MD5.java 128 2011-05-17 08:03:49Z zijing.zhang $
 */
public class MD5 {

    private static final Logger log = LoggerFactory.getLogger(MD5.class);

    public static String encode(String plaintext) {
        if(plaintext == null) {
            return null;
        }
        StringBuilder ret;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(plaintext.getBytes("utf8"));
            ret = new StringBuilder(bytes.length << 1);
            for (int i = 0; i < bytes.length; i++) {
                ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
                ret.append(Character.forDigit(bytes[i] & 0xf, 16));
            }
            return ret.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String str = "admin";
        System.out.println(MD5.encode(str));
    }

}
