package com.kc.goodlife.filter;

import com.kc.goodlife.bean.context.UserBean;
import com.kc.goodlife.service.login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author  liuyan
 *
 */
@Component
@WebFilter(urlPatterns = { "/**" }, filterName = "authFilter")
public class AuthFilter implements Filter {

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    private AuthService authService;


    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<String>(
            Arrays.asList("/auth/login", "/auth/logout", "/register","/swagger-ui.html")));

    private static final Set<String> TOKEN_IN_URL_PATHS = Collections.unmodifiableSet(new HashSet<String>(
            Arrays.asList("/export","/file/upload","/pipeInfo/upload","/pipeInfo/excelDownload","/devRepair/upload","/devRepair/download","/devUnit/upload","/file/download","/file/upImage","/file/downloadImg")));


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("进入");
        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        /*response.setHeader("Access-Control-Max-Age", "3600");*/
        response.setHeader("Access-Control-Allow-Headers","content-type, X-Auth-Token");

        //test
        if (env.equals("dev")) {
            UserBean userBean = new UserBean();

            userBean.setUserId(1);
            userBean.setUserName("test");

            request.setAttribute("user", userBean);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        String test = path.substring(0,4);
        if (request.getMethod().toUpperCase().equals("OPTIONS")
                || ALLOWED_PATHS.contains(path) ||test.equals("/web")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token;
        if (TOKEN_IN_URL_PATHS.contains(path)) {
            token = request.getParameter("token");
        }
        else {
            token = request.getHeader("X-Auth-Token");
        }
        if (token == null || token.equals("") || !authService.isTokenExist(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Long userId = authService.getUserIdByToken(token);
       /* UserManagerModel userManagerModel = userManagerService.getUserDetails(userId);

        UserBean userBean = new UserBean();

        userBean.setUserName(userManagerModel.getUserName());
        request.setAttribute("user", userBean);
        */
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }

}
