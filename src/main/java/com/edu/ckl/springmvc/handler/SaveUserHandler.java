package com.edu.ckl.springmvc.handler;

import com.edu.ckl.springmvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-22 16:13
 */
public class SaveUserHandler implements SimpleControllerHandler {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().write("湖人总冠军---SaveUserHandler");
        return null;    }
}
