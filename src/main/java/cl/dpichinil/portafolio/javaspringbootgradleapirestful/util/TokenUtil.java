package cl.dpichinil.portafolio.javaspringbootgradleapirestful.util;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.properties.ApplicationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class TokenUtil {

    public static String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
            if (username == null) username = "";
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Constant.KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private static Date generateExpirationDate(ApplicationProperties applicationProperties) {
        long expirationMillis = 0L;
        expirationMillis = applicationProperties.getJwtExpiration() * 1000;
        //expirationMillis = 60400 * 1000;
        return new Date(System.currentTimeMillis() + expirationMillis);
    }

    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public static String generateToken(String username, ApplicationProperties applicationProperties) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constant.CLAIM_KEY_USERNAME, username);
        claims.put(Constant.CLAIM_KEY_CREATED, new Date());
        return generateToken(claims, applicationProperties);
    }

    private static String generateToken(Map<String, Object> claims, ApplicationProperties appliApplicationProperties) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate(appliApplicationProperties))
                .signWith(SignatureAlgorithm.HS512, Constant.KEY)
                .compact();
    }

    public static String refreshToken(String token, ApplicationProperties applicationProperties) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(Constant.CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims, applicationProperties);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public static int validateToken(String token) {
        int resp = 1000;
        if (!isTokenExpired(token)) {
            resp = 0;
        } else {
            resp = 1001;
        }
        return resp;
    }
}
