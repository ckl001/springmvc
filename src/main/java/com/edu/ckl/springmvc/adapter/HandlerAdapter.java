package com.edu.ckl.springmvc.adapter;

import com.edu.ckl.springmvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenkanglin
 * @desc
 *      DispatcherServlet中用于将各种处理器（handler）都适配成统一的HandlerAdapter类型
 * @Date 2020-10-22 14:46
 */
public interface HandlerAdapter {

    /***
     * description:
     *      用于判断给定的处理器类型，是否是我找的是适配器支持的
     * @param handler
     * @return boolean
     */
    boolean supports(Object handler);

    /***
     * description:
     *      执行处理器的处理逻辑，并返回处理结果（通过处理器适配器去执行处理器）
     * @param handler
     * @param request
     * @param response
     * @return com.edu.ckl.springmvc.model.ModelAndView
     */
    ModelAndView handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
