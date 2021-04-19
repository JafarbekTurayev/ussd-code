package pdp.uz.lesson6.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import pdp.uz.lesson6.entity.Role;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private static final long expireTime = 1000 * 60 * 60 * 24;
    private static final String secretKey = "topSecret";

    public String generateToken(String username, Set<Role> role) {
        Date expireTime = new Date(System.currentTimeMillis() + JwtProvider.expireTime);
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireTime)
                .claim("roles", role)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();


    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

        } catch (Exception e) {
            return null;
        }
    }


}
