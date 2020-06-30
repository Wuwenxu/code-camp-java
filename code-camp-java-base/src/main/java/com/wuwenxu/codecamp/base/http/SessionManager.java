package com.wuwenxu.codecamp.base.http;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 会话管理
 *
 * @Author: zhangxq
 * @Date: 2019/9/17 15:05
 */
public class SessionManager {
    private static final Map<String, HttpSession> MANAGED_SESSIONS = new HashMap<>();
    private static final Map<String, String> ID_TO_SESSION_KEY_MAPPING = new HashMap<>();

    /**
     * 添加session
     *
     * @param mappingId
     * @param session
     */
    public synchronized static void addSessionById(String mappingId, HttpSession session) {
        ID_TO_SESSION_KEY_MAPPING.put(session.getId(), mappingId);
        MANAGED_SESSIONS.put(mappingId, session);
    }

    /**
     * 删除session 使用sessionId
     *
     * @param sessionId
     */
    public synchronized static void removeBySessionById(String sessionId) {
        String key = ID_TO_SESSION_KEY_MAPPING.get(sessionId);
        MANAGED_SESSIONS.remove(key);
        ID_TO_SESSION_KEY_MAPPING.remove(sessionId);
    }

    /**
     * 删除session 使用mappingId
     *
     * @param mappingId
     * @return
     */
    public synchronized static HttpSession removeSessionByMappingId(String mappingId) {
        HttpSession session = MANAGED_SESSIONS.get(mappingId);
        if (session != null) {
            removeBySessionById(session.getId());
        }
        return session;
    }
}
