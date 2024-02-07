package com.systex.common.util.encrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class Encryptor {

    private final static byte[] key = "pazzw0rdpazzw0rdpazzw0rdpazzw0rd".getBytes();
    private static final long timeOutMinutes = 15L;

    //    public static String encryptECB(UUID uuid) throws NoSuchPaddingException, NoSuchAlgorithmException,
    //            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    //        LocalDateTime timeOut = LocalDateTime.now().plusMinutes(timeOutMinutes);
    //        byte[] data = (uuid.getValue()+"|"+ timeOut).getBytes();
    //        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    //        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
    //        byte[] result = cipher.doFinal(data);
    //        String resultStr = Base64.encodeBase64String(result);
    //        return resultStr;
    //    }

    public static String encryptECB(String[] args, Date expiredTime)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        LocalDateTime timeOut = expiredTime.toInstant()
                                           .atZone(ZoneId.systemDefault())
                                           .toLocalDateTime();
        String dataStr = Arrays.stream(args)
                               .reduce((s, s2) -> s + "|" + s2)
                               .orElse(null);
        byte[] data = (dataStr + "|" + timeOut).getBytes();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
        byte[] result = cipher.doFinal(data);
        return Base64.encodeBase64String(result);
    }

    public static List<String> decryptECB(String dataStr)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] data = Base64.decodeBase64(dataStr);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
        byte[] result = cipher.doFinal(data);
        List<String> resultList = new ArrayList<>();
        String resultStr = new String(result);
        while (!resultStr.isEmpty()) {
            int index = resultStr.indexOf("|");
            if (index != -1) {
                resultList.add(resultStr.substring(0, index));
                resultStr = resultStr.substring(index + 1);
            } else {
                resultList.add(resultStr);
                break;
            }

        }
        return resultList;
    }
}
