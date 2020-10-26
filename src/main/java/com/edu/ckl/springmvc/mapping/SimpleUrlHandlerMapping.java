package com.edu.ckl.springmvc.mapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-22 17:36
 */
public class SimpleUrlHandlerMapping implements HandlerMapping {

    private Map<String,Object> urlHandlers = new HashMap<>();

    public SimpleUrlHandlerMapping() {
        // 方式2：SimpleUrlHandlerMapping
        // <bean class="专门用来建立映射关系的类">
        //  <props>
        //    <prop key="请求URL">处理器类的全路径</prop>
        //  </props>
        // </bean>
        // TODO
//        this.urlHandlers.put("/saveUser",new SaveUserHandler());
    }


    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if(uri == null || "".equals(uri)){
            return null;
        }
        return this.urlHandlers.get(uri);
    }
}
