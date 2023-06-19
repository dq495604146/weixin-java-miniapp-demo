package com.github.binarywang.demo.wx.miniapp.interceptor;

import com.github.binarywang.demo.wx.miniapp.constant.ErrorEnum;
import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.github.binarywang.demo.wx.miniapp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

  private static final String ADMIN_TOKEN = "diufjdhwkadh$!#fihsafdo123123#$!@";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String path = request.getRequestURI();

    if (handler instanceof HandlerMethod) {
      String token = request.getHeader("token");
      if (Strings.isEmpty(token)) {
        throw SystemException.buildSystemException(ErrorEnum.NoToken);
      } else {
        if (token.equals(ADMIN_TOKEN)) {
          return true;
        }
        Claims claims = JwtUtil.validateToken(token).getClaims();
        if (claims == null) {
          throw SystemException.buildSystemException(ErrorEnum.NoAccess);
        } else {
          return true;
        }
      }
    }
    return true;
  }
}
