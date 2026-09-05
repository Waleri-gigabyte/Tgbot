package org.example.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionStorage {
    private final Map<Long, UserSession> sessions = new ConcurrentHashMap<>();

    public UserSession getSession(Long userId) {
        return sessions.computeIfAbsent(userId, id -> new UserSession());
    }

    public void resetSession(Long userId) {
        sessions.put(userId, new UserSession());
    }

    public void removeSession(Long userId) {
        sessions.remove(userId);
    }
}
