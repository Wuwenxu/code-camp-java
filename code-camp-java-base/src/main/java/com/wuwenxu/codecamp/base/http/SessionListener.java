package com.wuwenxu.codecamp.base.http;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 会话监听
 *
 * 用于会话自动销毁时同时清空 系统维护的会话
 *
 * @Author: zhangxq
 * @Date: 2019/9/17 15:26
 */
@Component
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        SessionManager.removeBySessionById(session.getId());
    }
}
