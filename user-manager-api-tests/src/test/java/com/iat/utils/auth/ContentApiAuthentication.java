package com.iat.utils.auth;

import com.iat.Config;
import com.ning.http.util.Base64;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Slf4j
public class ContentApiAuthentication {

    private static final String ENCODING_FOR_ENCRYPTION = "UTF-8";

    public Headers auth(String path, String method) {
        return auth(Config.getEwsApiKey(), Config.getEwsSecret(), getProperPath(path), method);
    }

    public Headers auth(String apiKey, String secret, String path, String method) {
        String date = new Date().toString();
        String encodedHmac = secret != null ? prepareHmac(secret, path, method, date) : null;
        return new Headers(
                new Header("Authorization", String.format("IAT %s:%s", apiKey, encodedHmac)),
                new Header("X-IAT-Date", date)
        );
    }

    private String getProperPath(String path) {
        final int querySeparator = path.indexOf("?");
        String actualPath = querySeparator != -1 ? path.substring(0, querySeparator) : path;
        try {
            return new URI(actualPath).getPath();
        } catch (final URISyntaxException e) {
            throw new IllegalArgumentException("Invalid url created", e);
        }
    }

    private String prepareHmac(String secret, String path, String method, String date) {
        String message = String.format("%s\n%s\n%s", method, date, path);
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(ENCODING_FOR_ENCRYPTION), "HmacSHA256");
            Mac mac = getMac("HmacSHA256");
            mac.init(secretKey);
            byte[] hmac = mac.doFinal(message.getBytes(ENCODING_FOR_ENCRYPTION));
            return Base64.encode(hmac);
        } catch (InvalidKeyException | UnsupportedEncodingException e) {
            log.warn("Could not prepare hmac for path {}", path);
            return "InvalidKey";
        }
    }

    private static Mac getMac(String algorithm) throws IllegalArgumentException {
        try {
            return Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }
}
