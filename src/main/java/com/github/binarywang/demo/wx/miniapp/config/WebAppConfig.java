package com.github.binarywang.demo.wx.miniapp.config;

import com.github.binarywang.demo.wx.miniapp.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {}

  public JwtInterceptor jwtInterceptor() {
    return new JwtInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    String[] patterns = new String[] {"/user/login", "/error"};
    registry.addInterceptor(jwtInterceptor()).excludePathPatterns(patterns);
  }
}
