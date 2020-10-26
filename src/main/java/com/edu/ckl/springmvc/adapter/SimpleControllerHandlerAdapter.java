package com.edu.ckl.springmvc.adapter;

import com.edu.ckl.springmvc.handler.SimpleControllerHandler;
import com.edu.ckl.springmvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenkanglin
 * @desc
 *      专门来处理SimpleControllerHandler处理器类型
 * @Date 2020-10-22 16:08
 */
public class SimpleControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof SimpleControllerHandler);
    }

    @Override
    public ModelAndView handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ((SimpleControllerHandler)handler).handleRequest(request,response);
    }

}
