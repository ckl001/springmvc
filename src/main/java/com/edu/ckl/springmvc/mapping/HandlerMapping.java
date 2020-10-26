package com.edu.ckl.springmvc.mapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenkanglin
 * @desc
 *      对于HandlerMapping实现类抽取出来个共性操作方法
 * @Date 2020-10-21 17:09
 */
public interface HandlerMapping {

    Object getHandler(HttpServletRequest request);
}
