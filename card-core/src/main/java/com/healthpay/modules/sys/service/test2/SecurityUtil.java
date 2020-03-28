package com.healthpay.modules.sys.service.test2;

/**
 * Created by admin on 2018/10/16.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;


import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SecurityUtil {
    private static final String DES = "DES";

    public SecurityUtil() {
    }

    private static byte[] encrypt(byte[] src, byte[] key) throws RuntimeException {
        try {
            SecureRandom e = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, securekey, e);
            return cipher.doFinal(src);
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    private static byte[] decrypt(byte[] src, byte[] key) throws RuntimeException {
        try {
            SecureRandom e = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, securekey, e);
            return cipher.doFinal(src);
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public static final String decrypt(String data, String key) throws Exception {
        return new String(decrypt(hex2byte(data.getBytes("UTF-8")), key.getBytes("UTF-8")), "UTF-8");
    }

    public static final String encrypt(String data, String key) {
        if(data != null) {
            try {
                return byte2hex(encrypt(data.getBytes("UTF-8"), key.getBytes("UTF-8")));
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        } else {
            return null;
        }
    }

    private static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();

        for(int n = 0; b != null && n < b.length; ++n) {
            String stmp = Integer.toHexString(b[n] & 255);
            if(stmp.length() == 1) {
                hs.append('0');
            }

            hs.append(stmp);
        }

        return hs.toString();
    }

    private static byte[] hex2byte(byte[] b) {
        if(b.length % 2 != 0) {
            throw new IllegalArgumentException();
        } else {
            byte[] b2 = new byte[b.length / 2];

            for(int n = 0; n < b.length; n += 2) {
                String item = new String(b, n, 2);
                b2[n / 2] = (byte)Integer.parseInt(item, 16);
            }

            return b2;
        }
    }
}
