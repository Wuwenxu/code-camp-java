package com.wuwenxu.codecamp.base.file;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhaox
 * @Description
 * @createDate 2020/03/04 13:05
 */
public class LoginFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    private String serviceurl;
    private String ssologinUrl;
    private static final String USER = "userName";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        logger.info("地址：{} ", uri);
        // 判断是否登录，是否为验证票据地址
        if (uri.endsWith("/logout") || uri.endsWith("/login")) {
            logger.info("{} 不需要登录", req.getRequestURI());
            chain.doFilter(request, response);
            return;
        }
        //不会自动创建session
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute(USER) != null) {
            logger.info("{} 已登录", uri);
            chain.doFilter(request, response);
            return;
        } else {
            logger.info("{} 未登录", uri);
            JSONObject object = new JSONObject();
            object.put("errCode", 1105L);
            object.put("errMsg", "登录超时!");
            object.put("data", ssologinUrl + "?service=" + serviceurl);
            returnJson(response, JSONObject.toJSONString(object));
            return;
        }
    }

    @Override
    public void destroy() {
        // Do nothing
    }

    /**
     * 加载初始化参数
     *
     * @param fConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        serviceurl = fConfig.getInitParameter("serviceurl");
        ssologinUrl = fConfig.getInitParameter("ssologinUrl");
    }


    // 返回前台地址
    private void returnJson(ServletResponse response, String json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error {} ", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
