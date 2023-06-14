package com.github.binarywang.demo.wx.miniapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@SpringBootApplication
@MapperScan(basePackages ={"com.github.binarywang.demo.wx.miniapp.dao"})
public class WxMaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxMaDemoApplication.class, args);
    }
}
