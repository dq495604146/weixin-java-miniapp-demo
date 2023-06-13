package com.github.binarywang.demo.wx.miniapp.config;

import com.github.binarywang.demo.wx.miniapp.dto.ResponseDTO;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseDTOResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  private static final Set<Class<?>> NO_SUPPORTED_CLASSES = new HashSet<>(8);

  static {
    NO_SUPPORTED_CLASSES.add(ResponseDTO.class);
    NO_SUPPORTED_CLASSES.add(String.class);
    NO_SUPPORTED_CLASSES.add(byte[].class);
  }

  @Override
  public boolean supports(
      MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return !NO_SUPPORTED_CLASSES.contains(returnType.getParameterType());
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {
    return ResponseDTO.success(body);
  }

  @ExceptionHandler(SystemException.class)
  @ResponseBody
  public Object other(SystemException e) {
    return ResponseDTO.error(e.getCode(), e.getMsg());
  }
}
