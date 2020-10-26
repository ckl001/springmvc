package com.edu.ckl.springmvc.servlet;

import com.edu.ckl.springframeworkckl.factory.support.DefaultListableBeanFactory;
import com.edu.ckl.springframeworkckl.reader.XmlBeanDefinitionReader;
import com.edu.ckl.springframeworkckl.resource.ClasspathResource;
import com.edu.ckl.springframeworkckl.resource.Resource;
import com.edu.ckl.springmvc.adapter.HandlerAdapter;
import com.edu.ckl.springmvc.mapping.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      只需要搞一个servlet来分发所有请求
 *      前端控制器
 * @Date 2020-10-21 17:09
 */
public class DispatcherServlet extends AbstarctServlet{

    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 获取 web.xml contextConfigLocation 元素的 值
        String location = config.getInitParameter(CONTEXT_CONFIG_LOCATION);

        // 初始化spring容器
        initSpringContainer(location);

        // 初始化策略集合
        initStrategies();

    }

    private void initStrategies() {
        initHandlerMappings();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        // 可以根据类型进行一次性初始化所有的子类型的实例
        handlerAdapters = beanFactory.getBeansByType(HandlerAdapter.class);
    }

    private void initHandlerMappings() {
        handlerMappings = beanFactory.getBeansByType(HandlerMapping.class);

    }

    private void initSpringContainer(String location) {
        beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = new ClasspathResource(location);
        beanDefinitionReader.loadBeanDefinitions(resource);

        // 针对容器内说有的单例bean，可以一次性进行对象创建

    }

    // request -> mapping 根据 request 找到 handler --》 adapter 通过 handler 找到 handlerAdapter --》 handlerAdapter 处理 handler 的业务逻辑
    @Override
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 1、根据请求查找对应的【处理器】进行处理（处理器是啥？怎么找处理器）
            Object handler = getHeadler(request);

            if(handler == null){
                return;
            }


            // 2、根据处理器找到对应的适配器
            // 适配器 （DispatchServlet --》 adapter适配器 --》handler 处理器）
            // 适配器和处理器是 1v1
            HandlerAdapter ha = getHeadlerAdapter(handler);


            // 3、执行处理器的处理逻辑，并返回处理结果（通过处理器适配器去执行处理器）
            ha.handleRequest(handler, request, response);


            // 将处理结果通过response响应给客户端

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HandlerAdapter getHeadlerAdapter(Object handler) {
        // 使用策略模式
        if(handlerAdapters != null){
            for (HandlerAdapter handlerAdapter : handlerAdapters) {
                // 需要根据处理器类型判断那个适配器与他适配
                if(handlerAdapter.supports(handler)){
                    return handlerAdapter;
                }
            }
        }
        return null;
    }

    private Object getHeadler(HttpServletRequest request) {
        //根据处理器和请求的映射关系进行查找（映射关系可能存储在xml配置文件的标签中，可能存储到map集合中）
        // 方式1：BeanNameUrlHandlerMapping
        // <bean name="/queryUser" class="处理器类的全路径"/>

        // 方式2：SimpleUrlHandlerMapping
        // <bean class="专门用来建立映射关系的类">
        //  <props>
        //    <prop key="请求URL">处理器类的全路径</prop>
        //  </props>
        // </bean>


        // 先去方式1中查找
        // 如果找不到，则继续找
        // 再去方式2中查找
        // 如果找不到，则继续找

        if(handlerMappings != null){
            for (HandlerMapping handlerMapping : handlerMappings) {
                Object handler = handlerMapping.getHandler(request);
                if(handler!=null){
                    return handler;
                }
            }
        }

        return null;
    }
}
