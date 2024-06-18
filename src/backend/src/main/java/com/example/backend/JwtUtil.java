package com.example.backend;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    private static final String base64SecretBytes = Base64.getEncoder().encodeToString("secretKey".getBytes());
    private static final Key KEY = new SecretKeySpec(base64SecretBytes.getBytes(), SignatureAlgorithm.HS512.getJcaName());

    public static String generateToken(String username) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600000; // Token有效期，这里设置为1小时
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
//                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public static String getUsernameFromToken(String token) throws Exception {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            return claimsJws.getBody().getSubject();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Token验证失败");
        }
    }
}
