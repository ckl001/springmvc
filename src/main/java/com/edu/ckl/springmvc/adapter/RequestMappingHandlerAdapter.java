package com.edu.ckl.springmvc.adapter;

import com.edu.ckl.springmvc.annotation.ResponseBody;
import com.edu.ckl.springmvc.conversion.IntegerTypeHandler;
import com.edu.ckl.springmvc.conversion.StringTypeHandler;
import com.edu.ckl.springmvc.conversion.TypeHandler;
import com.edu.ckl.springmvc.model.HandlerMethod;
import com.edu.ckl.springmvc.model.ModelAndView;
import com.edu.ckl.springmvc.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @author chenkanglin
 * @desc
 *      专门处理 注解类型 处理器
 * @Date 2020-10-23 16:41
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {


    private Map<Class, TypeHandler> typeHandlers = new HashMap<>();


    public RequestMappingHandlerAdapter() {
        this.typeHandlers.put(String.class,new StringTypeHandler());
        this.typeHandlers.put(Integer.class,new IntegerTypeHandler());
        // 一次性建立所有类型的处理器
    }

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof HandlerMethod);
    }

    @Override
    public ModelAndView handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerMethod hm = (HandlerMethod) handler;
        Object controller = hm.getController();
        Method method = hm.getMethod();

        // 获取参数（解析参数）
        Object[] args = handleParameters(method,request);

        // 通过反射调用处理器方法处理请求
        Object returnValue = method.invoke(controller, args);

        // 处理返回结果
        handleReturnValue(returnValue, method, response);

        return null;
    }


    /***
     * description:
     *      处理返回值
     * @param returnValue
     * @param method
     * @param response
     * @return void
     */
    private void handleReturnValue(Object returnValue, Method method, HttpServletResponse response) throws IOException {
        if(method.isAnnotationPresent(ResponseBody.class)){
            if(returnValue instanceof String){
                response.setContentType("text/plain;charset=utf8");
                response.getWriter().write(returnValue.toString());
            }else if(returnValue instanceof Map){
                response.setContentType("application/json;charset=utf8");
                response.getWriter().write(JsonUtils.object2Json(returnValue));
            }//......
        }else {
            //视图处理
        }


    }

    private Object[] handleParameters(Method method, HttpServletRequest request) {
        List result = new ArrayList<>();

        // 获取Request请求参数（Key其实对应的是Method方法参数的名称）
        Map<String, String[]> parameterMap = request.getParameterMap();

        // 获取方法上的参数
        Parameter[] parameters = method.getParameters();

        // 获取到方法中的所有参数
        for (Parameter parameter : parameters) {

            // 获取参数名称（先埋个雷）
            // 通过反编译之后，获取到的参数名称，不做特殊处理的话，name值是arg0，arg1这样的值
            String name = parameter.getName();

            // 根据参数名称获取到参数值的数组
            String[] valueArray = parameterMap.get(name);

            if(valueArray == null || valueArray.length == 0){
                result.add(null);
                continue;
            }

            // 处理参数类型转换
            TypeHandler typeHandler = typeHandlers.get(parameter.getType());
            Object value = typeHandler.handleValue(valueArray);
            result.add(value);

        }

        return result.toArray();
    }
}
