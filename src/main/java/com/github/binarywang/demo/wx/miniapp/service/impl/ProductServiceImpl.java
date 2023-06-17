package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.demo.wx.miniapp.dao.ProductDao;
import com.github.binarywang.demo.wx.miniapp.entity.ProductEntity;
import com.github.binarywang.demo.wx.miniapp.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity>
    implements ProductService {
  @Override
  public List<ProductEntity> getAllProducts() {
    return list();
  }
}
