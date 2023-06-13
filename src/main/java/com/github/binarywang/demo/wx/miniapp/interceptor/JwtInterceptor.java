package com.github.binarywang.demo.wx.miniapp.interceptor;

import com.github.binarywang.demo.wx.miniapp.exception.SystemException;
import com.github.binarywang.demo.wx.miniapp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String path = request.getRequestURI();

    if (handler instanceof HandlerMethod) {
      String token = request.getHeader("token");
      if (Strings.isEmpty(token)) {
        throw new SystemException(401, "no token");
      } else {
        Claims claims = JwtUtil.validateToken(token).getClaims();
        if (claims == null) {
          throw new SystemException(401, "access denied");
        } else {
          return true;
        }
      }
    }
    return true;
  }
}
