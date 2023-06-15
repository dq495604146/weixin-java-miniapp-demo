package com.github.binarywang.demo.wx.miniapp.service;

import com.github.binarywang.demo.wx.miniapp.entity.ProductEntity;
import java.util.List;

public interface ProductService {
  List<ProductEntity> getAllProducts();
}
