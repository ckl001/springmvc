package com.edu.ckl.springmvc.model;

import java.lang.reflect.Method;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-23 15:52
 */
public class HandlerMethod {

    private Object controller;

    private Method method;

    public HandlerMethod(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
