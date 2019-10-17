package ru.nazarfatichov.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public String getJwt(String login){
        Map<String, Object> tokenData = new HashMap<>();

        tokenData.put("login", login);

        JwtBuilder jwtBuilder = Jwts.builder();

        jwtBuilder.setClaims(tokenData);
        String jwt = jwtBuilder.signWith(SignatureAlgorithm.HS256, "bla bla").compact();

        return jwt;
    }

}
