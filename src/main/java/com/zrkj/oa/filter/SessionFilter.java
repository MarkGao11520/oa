package com.zrkj.oa.filter;


import com.zrkj.oa.model.Login;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;


/**
 * 登录过滤
 *
 * @author gaowenfeng
 */
public class SessionFilter implements Filter {


    public void init(FilterConfig cfg) throws ServletException {

    }


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String exception[] = {"login", "login", "loginCheck", ".css", ".js", ".jpg", ".png", "login", "loginError"};
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Login sessionObj = (Login) request.getSession().getAttribute(exception[0]);
        String requestPath = request.getRequestURI();
        if (sessionObj == null && !requestPath.endsWith(exception[1]) && !requestPath.endsWith(exception[2]) && !requestPath.endsWith(exception[3]) && !requestPath.endsWith(exception[4]) && !requestPath.endsWith(exception[5]) && !requestPath.endsWith(exception[6])) {
            request.setAttribute(exception[8], "您还没有登录呢");
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            response.sendRedirect(basePath + exception[7]);
        } else {
            chain.doFilter(req, res);
        }
    }

    public void destroy() {
    }
}
