package com.edu.ckl.springmvc.adapter;

import com.edu.ckl.springmvc.handler.HttpServletHandler;
import com.edu.ckl.springmvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-22 16:04
 */
public class HttpServletHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof HttpServletHandler);
    }

    @Override
    public ModelAndView handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((HttpServletHandler)handler).handleRequest(request,response);
        return null;
    }
}
