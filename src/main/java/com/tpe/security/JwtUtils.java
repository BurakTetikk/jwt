package com.tpe.security;

import com.tpe.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    // 1 : JWT generate ( secret key + kullanıcıdan bilgi)
    // 2 : JWT validation
    // 3 : JWT --> token den userName alan method


    private String jwtSecret = "sboot";

    private long jwtExpirationMs = 86_400_000;  // 24*60*60*1000 -> 1 gün



    // ********** GENERATE TOKEN ************

    public String generateToken(Authentication authentication) {


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); // Security context ten anlık olarak login olan kullanıcıyı döndürür "UserDetails" döner "User" değil!!!


        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }


    //********* JWT VALIDATION **********

    public boolean validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

            return true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return false;

    }


    // ********* JWT --> userName *********

    public String getUserNameFromJwtToken(String token) {

        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }



}
