package com.github.binarywang.demo.wx.miniapp.utils;

import com.github.binarywang.demo.wx.miniapp.constant.WxUserConstant;
import com.github.binarywang.demo.wx.miniapp.dto.CheckResult;
import io.jsonwebtoken.*;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Base64;

public class JwtUtil {

  /**
   * 签发token
   *
   * @param id
   * @param subject
   * @param ttlMills
   * @return
   */
  public static String generateToken(String id, String subject, long ttlMills) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    SecretKey secretKey = generateKey();
    JwtBuilder builder =
        Jwts.builder()
            .setId(id)
            .setSubject(subject) // 主体
            .setIssuer("eto") // 签发人
            .setIssuedAt(now) // 签发时间
            .signWith(signatureAlgorithm, secretKey); // 签名算法及密钥
    if (ttlMills >= 0) {
      long expireMills = nowMillis + ttlMills;
      Date expireDate = new Date(expireMills);
      builder.setExpiration(expireDate); // 过期时间
    }
    return builder.compact();
  }

  /**
   * token 验证
   *
   * @param token
   * @return
   */
  public static CheckResult validateToken(String token) {
    CheckResult checkResult = new CheckResult();
    Claims claims;

    try {
      claims = parseToken(token);
      checkResult.setSuccess(true);
      checkResult.setClaims(claims);
    } catch (ExpiredJwtException e) {
      checkResult.setErrCode(WxUserConstant.TOKEN_IS_EXPIRE);
      checkResult.setSuccess(false);
    } catch (Exception e) {
      checkResult.setErrCode(WxUserConstant.TOKEN_IS_FAIL);
      checkResult.setSuccess(false);
    }
    return checkResult;
  }

  private static SecretKey generateKey() {
    byte[] decode = Base64.decode(WxUserConstant.Jwt_Secret);
    return new SecretKeySpec(decode, 0, decode.length, "AES");
  }

  /**
   * 解析 token
   *
   * @param token
   * @return
   * @throws Exception
   */
  public static Claims parseToken(String token) throws Exception {
    SecretKey secretKey = generateKey();
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  public static void main(String[] args) throws InterruptedException {
    // 小明失效 2s
    String sc = generateToken("1", "小明", 2 * 1000);
    System.out.println(validateToken(sc).isSuccess());
    Thread.sleep(3000);
    System.out.println(validateToken(sc).isSuccess());
  }
}
