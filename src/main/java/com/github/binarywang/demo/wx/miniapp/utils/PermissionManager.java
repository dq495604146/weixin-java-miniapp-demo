package com.github.binarywang.demo.wx.miniapp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

  public short productIdList2productFlag(List<Integer> productIds) {
    for (int productId : productIds) {
      grantPermission(productId);
    }
    return permissionMask;
  }

  public List<Integer> productFlag2productIdList(short num) {
    List<Integer> onesList = new ArrayList<>();

    for (int i = 0; i < 16; i++) {
      short mask = (short) (1 << i);
      if ((num & mask) != 0) {
        onesList.add(i);
      }
    }
    return onesList;
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

    short code = permissionManager.productIdList2productFlag(Arrays.asList(1, 2));
    System.out.println("权限码:" + code);

    System.out.println(permissionManager.productFlag2productIdList((short) 6));
  }
}
