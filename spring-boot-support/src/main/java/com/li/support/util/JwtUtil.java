package com.li.support.util;

import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    final static String SECURITY_KEY = "security";
    final static long TOKEN_TIME = 1000 * 60;

    public static String getToken(String userName) {
        return Jwts.builder().setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_TIME))
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                .compact();
    }

    public static Claims checkToken(String token) {
        try{
            final Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException e1) {
            throw new ServiceException(ErrorCodeEnum.USED_CODE);
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeEnum.INVALID_CODE);
        }
    }
}
