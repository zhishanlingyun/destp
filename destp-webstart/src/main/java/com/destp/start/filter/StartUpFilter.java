package com.destp.start.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/25 0025.
 */
@WebFilter(urlPatterns = {"/*"},
        dispatcherTypes = {DispatcherType.REQUEST},
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8")}
)
public class StartUpFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------");

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    public void destroy() {

    }
}
