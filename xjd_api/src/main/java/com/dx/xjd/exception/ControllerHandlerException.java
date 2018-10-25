package com.dx.xjd.exception;

import com.alibaba.fastjson.JSONObject;
import com.dx.xjd.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ControllerHandlerException {

    @Autowired
    private Constants constants;

    @ExceptionHandler(MleAuthenticationException.class)
    //将返回的值转成json格式的数据
    @ResponseBody
    //返回的状态码
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject handlerUserNotExistException(MleAuthenticationException ex){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getMESSAGE(), ex.getMessage());
        log.error("MLE异常",ex);
        return jsonObject;
    }

    @ExceptionHandler(Exception.class)
    //将返回的值转成json格式的数据
    @ResponseBody
    //返回的状态码
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject handlerExistException(Exception ex){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getMESSAGE(), ex.getMessage());
        log.error("系统异常",ex);
        return jsonObject;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //将返回的值转成json格式的数据
    @ResponseBody
    //返回的状态码
    @ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
    public JSONObject handlerExistMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        BindingResult bindingResult = ex.getBindingResult();
        if(bindingResult.hasErrors()){
            String message = "";
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for(ObjectError objectError:allErrors) {
                message += objectError.getDefaultMessage()+"! ";
            }
            jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_NOT_ACCEPTABLE);
            jsonObject.put(message, message);
        }
        log.error("参数校验异常",ex);
        return jsonObject;
    }

}
