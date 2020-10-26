package com.edu.ckl.springmvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-22 16:12
 */
public class QueryUserHandler implements HttpServletHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().write("湖人总冠军---QueryUserHandler");
    }
}
