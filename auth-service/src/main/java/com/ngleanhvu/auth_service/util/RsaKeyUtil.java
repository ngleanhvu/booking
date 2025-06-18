package com.ngleanhvu.auth_service.util;

import java.nio.file.*;
import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;
import java.util.Base64;

public class RsaKeyUtil {
    private static final Path PRIVATE_PATH = Path.of("keys/private.pem");
    private static final Path PUBLIC_PATH = Path.of("keys/public.pem");

    public static RSAPrivateKey getPrivateKey() throws Exception {
        String content = Files.readString(PRIVATE_PATH)
                .replaceAll("-----\\w+ PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] bytes = Base64.getDecoder().decode(content);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(bytes));
    }

    public static RSAPublicKey getPublicKey() throws Exception {
        String content = Files.readString(PUBLIC_PATH)
                .replaceAll("-----\\w+ PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] bytes = Base64.getDecoder().decode(content);
        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(bytes));
    }
}

