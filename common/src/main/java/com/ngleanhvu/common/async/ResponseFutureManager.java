package com.ngleanhvu.common.async;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseFutureManager {
    private static final Map<String, CompletableFuture<String>> responseMap = new ConcurrentHashMap<>();

    public static String createFuture() {
        String id = UUID.randomUUID().toString();
        responseMap.put(id, new CompletableFuture<>());
        return id;
    }

    public static CompletableFuture<String> getFuture(String id) {
        return responseMap.get(id);
    }

    public static void complete(String id, String result) {
        CompletableFuture<String> future = responseMap.get(id);
        if (future != null) {
            future.complete(result);
        }
    }

    public static void remove(String id) {
        responseMap.remove(id);
    }
}
