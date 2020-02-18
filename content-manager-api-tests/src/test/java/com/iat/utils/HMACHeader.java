package com.iat.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by lpanusz on 2015-11-27.
 */
public class HMACHeader {

    private String epointsSecretQA = "3d6b002f08af5d719611908364f673ed";//"73395af801fb19866554d49431a1609d";
    private String epointsApiKeyQA = "00c15942786d3ceb3ad0629ec2a84493";
    private static final String ENCODING_FOR_ENCRYPTION = "UTF-8";

    //Auth header: "Authorization", asList(String.format("IAT %s:%s", apiKey, encodedHmac)),
    //Date header: "X-IAT-Date", asList(date))
    /*
    Example usage:
        String date = hmacHandler.generateHttpDate();
        String hmacHeader = hmacHandler.getHmacHeader("GET", null, null, "/user", date);
        System.out.printf("Generate date: %s \n", date);
        System.out.printf("Generated Hmac: %s \n", hmacHeader);
    */
    public String getHmacHeader(String method, String secret, String apiKey, String path, String date) {
        if ((secret == null) || (apiKey == null)) {
            secret = this.epointsSecretQA;
            apiKey = this.epointsApiKeyQA;
        }

        String encodedHmac = prepareHmac(method, secret, path, date);
        System.out.println("Header: IAT " + apiKey + ":" + encodedHmac);
        return String.format("IAT %s:%s", apiKey, encodedHmac);
    }

    public String generateHttpDate() {
        return new Date().toString();
    }

    private String prepareHmac(String method, String secret, String path, String date) {
        String message = String.format("%s\n%s\n%s",method, date, path);
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(ENCODING_FOR_ENCRYPTION), "HmacSHA256");
            Mac mac = getMac("HmacSHA256");
            mac.init(secretKey);
            byte[] hmac = mac.doFinal(message.getBytes(ENCODING_FOR_ENCRYPTION));
            return Base64.encodeBase64String(hmac);
        } catch (InvalidKeyException | UnsupportedEncodingException e) {
            return "InvalidKey";
        }
    }

    protected static Mac getMac(String algorithm) throws IllegalArgumentException {
        try {
            return Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }
}
