package com.edu.ckl.springmvc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-21 17:10
 */
public abstract class AbstarctServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    /***
     * description:
     *      使用抽象方法进行抽离
     * @param request
     * @param response
     * @return void
     */
    public abstract void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException;
}
