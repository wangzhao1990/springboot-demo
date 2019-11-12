package com.zhao.demo.common.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * @author wangzhao
 * 2019-10-21
 * <p>
 * JWT工具类
 */
public class JWTUtil {
    /*
     * 生成签名的时候使用的秘钥 secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。
     * 它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发 jwt 了。
     * 该值根据具体情况可改，此处写死只是临时举例用。
     */
    private static final String SECRET_KEY = "123456";

    /*
     * 默认过期时间： 24 小时
     */
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    public static String createToken(String username, String password) {
        return JWTUtil.createToken(null, username, password);
    }

    /**
     * 用户登录成功后使用 HS256 算法生成 token，ttlMillis 秒后
     * 在 token 中存入用户登录的登录名 LoginUserName
     */
    public static String createToken(Long ttlMillis, String username) {

        ttlMillis = MoreObjects.firstNonNull(ttlMillis, EXPIRE_TIME);

        Date date = new Date(System.currentTimeMillis() + ttlMillis);
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withClaim("username", username) // 附带 username 信息
                .withExpiresAt(date)                   // 到期时间
                .sign(algorithm);                      // 创建一个新的 JWT，并使用给定的算法进行标记
    }

    /**
     * 用户登录成功后使用 HS256 算法生成 token，ttlMillis 秒后
     * 在 token 中存入用户登录的登录名 LoginUserName
     */
    public static String createToken(Long ttlMillis, String userId, String password) {

        ttlMillis = MoreObjects.firstNonNull(ttlMillis, EXPIRE_TIME);

        Date date = new Date(System.currentTimeMillis() + ttlMillis);
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withClaim("user-id", userId) // 附带 userId 信息
                .withClaim("user-pwd", password) // password
                .withExpiresAt(date)                   // 到期时间
                .sign(algorithm);                      // 创建一个新的 JWT，并使用给定的算法进行标记
    }

    /**
     * 校验 token 是否失效
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            // 验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 校验 token 是否正确
     */
    public static boolean verify(String token, String userId, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("user-id", userId)
                    .withClaim("user-pwd", password)
                    .build();
            // 验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 解析 token，从中获得 username，无需 SECRET_KEY 解密也能获得
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 解析 token，从中获得 user-pwd，无需 SECRET_KEY 解密也能获得
     */
    public static String getUserPwd(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("user-pwd").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 解析 token，从中获得 user-id，无需 SECRET_KEY 解密也能获得
     */
    public static String getUserID(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("user-id").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String wangzhao = JWTUtil.createToken(null, "wangzhao", "password");
        System.out.println(wangzhao);
//        DecodedJWT decode = JWT.decode(wangzhao);
//        String payload = decode.getPayload();
//        System.out.println(payload);
//        Claim username = decode.getClaim("user-id");
//        System.out.println(username.asString());
//        System.out.println(decode.getClaim("user-pwd").asString());
//        Thread.sleep(1000L);
        System.out.println(JWTUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyLWlkIjoid2FuZ3poYW8iLCJ1c2VyLXB3ZCI6InBhc3N3b3JkIiwiZXhwIjoxNTcxOTgxNTEwfH.dMPE_h8iM1dn0HsUIRLVXGfnMj_baK6Czxbh8Yal_rI"));
//        System.out.println(JWTUtil.verify(wangzhao,"wangzhao"));
    }

}
