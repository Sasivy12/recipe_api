package com.example.recipe_api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService
{
    private String secret_key = "";

    public JwtService()
    {
        try
        {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk_key = keyGenerator.generateKey();
            secret_key = Base64.getEncoder().encodeToString(sk_key.getEncoded());
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username)
    {
        Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1800000))
                .and()
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver)
    {
        final Claims claims = extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = extractUsername(token);

        if(username.equals(userDetails.getUsername()) && !tokenIsExpired(token))
        {
            return true;
        }

        return false;
    }

    private boolean tokenIsExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }
}
