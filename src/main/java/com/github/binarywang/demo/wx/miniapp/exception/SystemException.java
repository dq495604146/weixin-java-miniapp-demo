package com.github.binarywang.demo.wx.miniapp.exception;

import com.github.binarywang.demo.wx.miniapp.constant.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class SystemException extends Exception {
  private int code;
  private String msg;

  public static SystemException buildSystemException(ErrorEnum errorEnum) {
    return new SystemException(errorEnum.getCode(), errorEnum.getMsg());
  }
}
