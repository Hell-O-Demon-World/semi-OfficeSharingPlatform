package com.golfzonaca.backoffice.auth.token;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JwtRepository {
    private static final Map<String, String> store = new HashMap<>();
    public Map<String, String> save (String userId, String token) {
        store.put(userId, token);
        return store;
    }
    public Map<String, String> getTokenMap() {
        return store;
    }
    public String getId() {
        Iterator<String> keys = store.keySet().iterator();
        String id = "";
        if ( keys.hasNext() ) {
            id = keys.next();
        }
        return id;
    }

    public String getToken(String userId) {
        return store.get(userId);
    }
    public void clearAll() {
        store.clear();
    }
}
