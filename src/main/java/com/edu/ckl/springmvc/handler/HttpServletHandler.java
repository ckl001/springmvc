package com.edu.ckl.springmvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenkanglin
 * @desc
 *      制定一种处理器的标准
 * @Date 2020-10-22 16:05
 */
public interface HttpServletHandler {

    /***
     * description:
     *
     * @param request
     * @param response
     * @return void
     */
    void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
