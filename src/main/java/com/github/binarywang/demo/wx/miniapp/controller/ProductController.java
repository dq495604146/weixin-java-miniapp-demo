package com.github.binarywang.demo.wx.miniapp.controller;

import com.github.binarywang.demo.wx.miniapp.entity.ProductEntity;
import com.github.binarywang.demo.wx.miniapp.service.ProductService;
import java.util.List;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {

  @Resource ProductService productService;

  @GetMapping("/all")
  public List<ProductEntity> getAllProducts() {
    return productService.getAllProducts();
  }
}
