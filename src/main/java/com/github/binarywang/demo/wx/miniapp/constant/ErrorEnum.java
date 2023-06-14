package com.github.binarywang.demo.wx.miniapp.constant;

import lombok.Getter;

public enum ErrorEnum {
  SystemError(1000, "system error"),
  UserNotFound(1001, "user not found"),
  NoToken(4001, "no token"),
  NoAccess(4002, "access deny"),
  PrePayError(2001, "prepay error");

  @Getter int code;

  @Getter String msg;

  ErrorEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
