package com.ngleanhvu.auth_service.util;

import java.nio.file.*;
import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;
import java.util.Base64;

public class RsaKeyUtil {
    private static final Path PRIVATE_PATH = Path.of("keys/private.pem");
    private static final Path PUBLIC_PATH = Path.of("keys/public.pem");

    static {
        try {
            if (!Files.exists(PRIVATE_PATH) || !Files.exists(PUBLIC_PATH)) {
                generateKeys();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateKeys() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        KeyPair pair = gen.generateKeyPair();

        Files.createDirectories(PRIVATE_PATH.getParent());

        String privatePem = encodePem(pair.getPrivate(), "PRIVATE KEY");
        String publicPem = encodePem(pair.getPublic(), "PUBLIC KEY");

        Files.writeString(PRIVATE_PATH, privatePem);
        Files.writeString(PUBLIC_PATH, publicPem);
    }

    private static String encodePem(Key key, String type) {
        return "-----BEGIN " + type + "-----\n" +
                Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(key.getEncoded()) +
                "\n-----END " + type + "-----\n";
    }

    public static RSAPrivateKey getPrivateKey() throws Exception {
        String content = Files.readString(PRIVATE_PATH)
                .replaceAll("-----\\w+ PRIVATE KEY-----", "").replaceAll("\\s", "");
        byte[] bytes = Base64.getDecoder().decode(content);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(bytes));
    }

    public static RSAPublicKey getPublicKey() throws Exception {
        String content = Files.readString(PUBLIC_PATH)
                .replaceAll("-----\\w+ PUBLIC KEY-----", "").replaceAll("\\s", "");
        byte[] bytes = Base64.getDecoder().decode(content);
        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(bytes));
    }
}

