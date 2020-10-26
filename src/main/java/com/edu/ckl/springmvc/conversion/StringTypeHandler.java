package com.edu.ckl.springmvc.conversion;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-23 16:44
 */
public class StringTypeHandler implements TypeHandler {
    @Override
    public Object handleValue(String[] valueArray) {
        return valueArray[0];
    }
}
