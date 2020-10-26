package com.edu.ckl.springmvc.mapping;

import com.edu.ckl.springframeworkckl.aware.BeanFactoryAware;
import com.edu.ckl.springframeworkckl.factory.BeanFactory;
import com.edu.ckl.springframeworkckl.factory.support.DefaultListableBeanFactory;
import com.edu.ckl.springframeworkckl.init.InitializingBean;
import com.edu.ckl.springframeworkckl.ioc.BeanDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-23 10:36
 */
public class BeanNameUrlHandlerMapping implements HandlerMapping, BeanFactoryAware, InitializingBean {

    private Map<String,Object> urlHandlers = new HashMap<>();


    // 思考：BeanFactory如何注入
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
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String beanName = beanDefinition.getBeanName();
            if(beanName.startsWith("/")){
                this.urlHandlers.put(beanName,beanFactory.getBean(beanName));
            }
        }

    }


}
