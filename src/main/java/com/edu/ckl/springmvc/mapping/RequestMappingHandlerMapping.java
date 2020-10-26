package com.edu.ckl.springmvc.mapping;

import com.edu.ckl.springframeworkckl.aware.BeanFactoryAware;
import com.edu.ckl.springframeworkckl.factory.BeanFactory;
import com.edu.ckl.springframeworkckl.factory.support.DefaultListableBeanFactory;
import com.edu.ckl.springframeworkckl.init.InitializingBean;
import com.edu.ckl.springframeworkckl.ioc.BeanDefinition;
import com.edu.ckl.springmvc.annotation.Controller;
import com.edu.ckl.springmvc.annotation.RequestMapping;
import com.edu.ckl.springmvc.model.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-23 15:52
 */
public class RequestMappingHandlerMapping implements HandlerMapping, BeanFactoryAware, InitializingBean {


    //类，方法
    private Map<String, HandlerMethod> urlHandlers = new HashMap<>();

    private DefaultListableBeanFactory beanFactory;


    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri = request.getServletPath();
        if (uri == null || "".equals(uri)){
            return null;
        }
        return this.urlHandlers.get(uri);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void afterPropertiesSet() {
        List<BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        for (BeanDefinition bd : beanDefinitions) {
            // 获取对应的bean类型
            Class<?> clazzType = bd.getClazzType();
            // 是否带有 @Controller 或 @Requestmapping 注解的类
            if(isHandler(clazzType)){
                Method[] methods = clazzType.getMethods();
                for (Method method : methods) {
                    if(method.isAnnotationPresent(RequestMapping.class)){
                        String url = combine(clazzType, method);
                        HandlerMethod hm = new HandlerMethod(beanFactory.getBean(bd.getBeanName()), method);
                        this.urlHandlers.put(url,hm);
                    }
                }
            }
        }
    }


    private String combine(Class<?> handler, Method method) {
        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
        String methodUrl = methodRequestMapping.value();

        RequestMapping clazzRequestMapping = handler.getAnnotation(RequestMapping.class);
        String clazzUrl = null;

        // 获取类上RequestMapping注解中的url
        if(clazzRequestMapping != null){
            clazzUrl = clazzRequestMapping.value();
        }

        StringBuffer sb = new StringBuffer();
        if(clazzUrl != null && !clazzUrl.equals("")){
            if(!clazzUrl.startsWith("/")){
                sb.append("/");
            }
            sb.append(clazzUrl);
        }

        if(!methodUrl.startsWith("/") && !(clazzUrl != null && clazzUrl.endsWith("/"))){
            sb.append("/");
        }
        sb.append(methodUrl);

        return sb.toString();
    }


    private boolean isHandler(Class<?> handler) {
        return (handler.isAnnotationPresent(Controller.class))
                || handler.isAnnotationPresent(RequestMapping.class);
    }
}
