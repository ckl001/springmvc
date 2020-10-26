package com.edu.ckl.springmvc.controller;

import com.edu.ckl.springmvc.annotation.Controller;
import com.edu.ckl.springmvc.annotation.RequestMapping;
import com.edu.ckl.springmvc.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 * 注解方式开发的处理器，谁是真正的处理器？？？
 * 回答1：使用@Controller标记类是处理器（false）
 * 回答2：使用@Controller标记的类中使用@RequestMapping标记的方法（true）
 *       HandlerMethod才是注解方式开发方式真正的处理器
 *       包含：Controller对象和Method对象
 *
 * 1、需要解析RequestMapping注解中的请求URL，建立HandlerMethod与请求的映射关系
 * 2、通过HandlerMethod封装的数据，借助于反射技术，完成方法调用
 *
 * @Date 2020-10-23 15:42
 */
@Controller
@RequestMapping("user/")
public class UserController {

    @RequestMapping("query")
    @ResponseBody
    public Map<String,Object> query(Integer id,String name){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        return map;
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(){
        return "添加成功";
    }

}
