package com.github.binarywang.demo.wx.miniapp.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {
  private int code;
  private String msg;
  private T data;

  public static <T> ResponseDTO<T> success(T data) {
    return success(data, "success");
  }

  public static <T> ResponseDTO<T> success(T data, String msg) {
    ResponseDTO<T> result = new ResponseDTO<>();
    result.setMsg(msg);
    result.setData(data);
    return result;
  }

  public static <T> ResponseDTO<T> error(String msg) {
    return error(1000, msg);
  }

  public static <T> ResponseDTO<T> error(int code, String msg) {
    return error(code, msg, null);
  }

  public static <T> ResponseDTO<T> error(int code, String msg, T data) {
    ResponseDTO<T> result = new ResponseDTO<>();
    result.setCode(code);
    result.setMsg(msg);
    result.setData(data);
    return result;
  }
}
