package com.github.binarywang.demo.wx.miniapp.utils;

import cn.hutool.core.bean.BeanUtil;
import java.util.Date;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtil {
  public Map<String, Object> objectToMapWithCustomDateFormat(Object object) {
    Map<String, Object> map = BeanUtil.beanToMap(object);
    map.forEach(
        (key, value) -> {
          if (value instanceof Date) {
            long time = ((Date) value).getTime();
            map.put(key, time);
          }
        });

    return map;
  }
}
