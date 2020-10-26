package com.edu.ckl.springmvc.handler;

import com.edu.ckl.springmvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-22 16:08
 */
public interface SimpleControllerHandler {

    ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
