package com.github.binarywang.demo.wx.miniapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SystemException extends Exception {
  private int code;
  private String msg;
}
