package com.example.demo.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: backend
 * @description: token生成工具
 * @author: 0GGmr0
 * @create: 2019-01-31 21:15
 */

public class JwtTools {
    private static final byte[] ENCODE_KEY = "dbhk".getBytes();
    private static JWTVerifier jwtVerifier;

    public static String createJwt(String subject) {
        Date currentDate = new Date();
        // 过期时间5天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        Algorithm algorithm = Algorithm.HMAC512(ENCODE_KEY);

        return JWT.create()
                .withIssuedAt(currentDate)
                .withExpiresAt(calendar.getTime())
                .withSubject(subject)
                .sign(algorithm);
    }

    public static String parseJwt(String jwt) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC512(ENCODE_KEY);
        if (jwtVerifier == null) {
            jwtVerifier = JWT.require(algorithm).build();
        }
        jwtVerifier.verify(jwt);
        return JWT.decode(jwt).getSubject();
    }

}