package com.edu.ckl.springmvc.conversion;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-23 16:44
 */
public class IntegerTypeHandler implements TypeHandler {
    @Override
    public Object handleValue(String[] valueArray) {
        return Integer.parseInt(valueArray[0]);
    }
}
