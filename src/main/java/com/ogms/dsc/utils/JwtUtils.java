package com.ogms.dsc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ogms.dsc.domain.constants.Constants;
import com.ogms.dsc.domain.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @name: JwtUtils
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 5/9/2024 11:37 AM
 * @version: 1.0
 */


public class JwtUtils {

    public static String createToken(User user) {
        // 获取jwt生成器
        JWTCreator.Builder jwtBuilder = JWT.create();
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "jwt");   //设置token的type为jwt
        headers.put("alg", "hs256");  //表明加密的算法为HS256
        // 开始生成token
        String token = jwtBuilder.withHeader(headers).withClaim("id", user.getId())
                .withClaim("email", user.getEmail()).withClaim("type", user.getType())
                //token失效时间，这里为一天后失效
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                //设置该jwt的发行时间，一般为当前系统时间
                .withIssuedAt(new Date(System.currentTimeMillis()))
                //token的发行者（可自定义）
                .withIssuer(Constants.JWT_ISSUER)
                //进行签名，选择加密算法，以一个字符串密钥为参数
                .sign(Algorithm.HMAC256(Constants.JWT_KEY));
        return token;
    }

    public static Map<String, Object> decodeToken(String token) {
        if (token != null) {
            Map<String, Object> map = new HashMap<>();
            JWTVerifier v = JWT.require(Algorithm.HMAC256(Constants.JWT_KEY)).build();
            try {
                DecodedJWT decodedJWT = v.verify(token);
                map.put("id", decodedJWT.getClaim("id").asInt());
                map.put("email", decodedJWT.getClaim("email").asString());
                map.put("type", decodedJWT.getClaim("type").asBoolean());
            } catch (TokenExpiredException e) {
                map.put("msg", e.getMessage());
                return map;
            } finally {
                return map;
            }
        }
        return null;
    }

    public static String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // 去除"Bearer "前缀
        }
        return null;
    }
}
