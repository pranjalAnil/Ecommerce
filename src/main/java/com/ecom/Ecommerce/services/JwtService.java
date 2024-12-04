package com.ecom.Ecommerce.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private String secretKey = "m0xPehTbOIOwAjfFTH8fjCAP6jtzKGNbzuY+gmpY6eI=";
/*    public JwtService() throws NoSuchAlgorithmException {
     KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
     SecretKey secretKey = keyGenerator.generateKey();
     this.secretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

    }*/

    public String generateToken(UserDetails userDetails) {

       Map<String, Object> claims =new HashMap<>();
       claims.put("role", userDetails.getAuthorities().stream()
               .map(GrantedAuthority::getAuthority)
               .collect(Collectors.toList()));
        System.out.println(claims);

     /*   String jwt = Jwts.builder()
                .setClaims(claims) // Set claims correctly
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 minutes expiration
                .signWith(getKey()) // Use the signing key
                .compact();
        System.out.println("Generated JWT: " + jwt);
        return jwt;
    }*/

       return Jwts.builder()
               .claims()
               .add(claims).subject(userDetails.getUsername())
               .issuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
               .and()
               .signWith(getKey())
               .compact();

    }

/*    public String extractRole(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.get("role",String.class);
        } catch (Exception e) {
            System.err.println("JWT Parsing Error: " + e.getMessage());
            throw new RuntimeException("JWT parsing failed", e);
        }
    }*/

    private SecretKey getKey() {
      // byte[] keyByte= Decoders.BASE64.decode(secretKey);
     //   System.out.println("Secret key: " + secretKey);
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String extractUsername(String token) {
      Claims claims = extractAllClaims(token);
      return claims.getSubject();
    }

    public Date extractExpiration(String token) {
      return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {

        try {
            return Jwts.parser() // Correct method to parse JWT
                    .setSigningKey(getKey()) // Set the signing key
                    .build()
                    .parseClaimsJws(token) // Use parseClaimsJws
                    .getBody(); // Extract the claims body
        } catch (Exception e) {
            throw new RuntimeException("JWT parsing failed",e);
        }
   /*     try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException("JWT parsing failed",e);
        }*/
    }

    private boolean isTokenExpired(String token) {
       return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token) {
       return !isTokenExpired(token);
    }

}
