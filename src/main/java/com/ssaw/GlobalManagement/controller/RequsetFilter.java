package com.ssaw.GlobalManagement.controller;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@WebFilter("*")
public class RequsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if (req.getRequestURI().equals("/system/checkLogin")
        ||req.getRequestURI().equals("/page/login")
        ||req.getRequestURI().startsWith("/api")
        ||req.getRequestURI().startsWith("/css")
        ||req.getRequestURI().startsWith("/exts")
        ||req.getRequestURI().startsWith("/fonts")
        ||req.getRequestURI().startsWith("/images")
        ||req.getRequestURI().startsWith("/js")
        ||req.getRequestURI().startsWith("/lib")
        ||req.getRequestURI().startsWith("/selectFund")
        ){
            filterChain.doFilter(req,resp);
        }else if (session!=null
                &&session.getAttribute("userName")!=null
                &&!session.getAttribute("userName").equals("")){
            filterChain.doFilter(req,resp);
        }else {
            if (req.getRequestURI().startsWith("/page")){
                resp.sendRedirect("login");
            }else {
                resp.sendRedirect("./page/login");
            }
        }


    }

    @Override
    public void destroy() {

    }
}
