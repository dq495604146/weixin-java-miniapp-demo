package com.github.binarywang.demo.wx.miniapp.utils;

import lombok.Getter;

public class PermissionManager {
  @Getter private short permissionMask;

  public PermissionManager() {
    this.permissionMask = 0;
  }

  public void grantPermission(int permission) {
    permissionMask |= (1 << permission);
  }

  public void revokePermission(int permission) {
    permissionMask &= ~(1 << permission);
  }

  public boolean hasPermission(int permission) {
    return (permissionMask & (1 << permission)) != 0;
  }

  public static void main(String[] args) {
    PermissionManager permissionManager = new PermissionManager();

    // 授予权限
    permissionManager.grantPermission(0);
    permissionManager.grantPermission(1);

    // 检查权限
    System.out.println("Has permission 0: " + permissionManager.hasPermission(0)); // true
    System.out.println("Has permission 1: " + permissionManager.hasPermission(1)); // true
    System.out.println("Has permission 2: " + permissionManager.hasPermission(2)); // false

    // 撤销权限
    permissionManager.revokePermission(1);

    // 检查权限
    System.out.println("Has permission 1: " + permissionManager.hasPermission(1)); // false
  }
}
