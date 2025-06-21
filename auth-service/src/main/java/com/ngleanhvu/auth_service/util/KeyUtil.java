package com.ngleanhvu.auth_service.util;

import java.security.NoSuchAlgorithmException;

public class KeyUtil {
    public static String generateRefreshKey(String authId, String jti)  {
        return "refresh:" + authId + ":jti:" + jti;
    }

    public static String blackListKey(String jti) {
        return "blacklist:" + jti;
    }

}
