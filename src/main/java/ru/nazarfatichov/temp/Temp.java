package ru.nazarfatichov.temp;

import ch.qos.logback.core.joran.spi.DefaultClass;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.HashMap;
import java.util.Map;

public class Temp {

    public static void main(String[] args) {

        JwtBuilder jwtBuilder = Jwts.builder();

        Map<String, Object> payload = new HashMap<>();

        payload.put("login", "nazar");
        payload.put("password", "123");

        jwtBuilder.setClaims(payload);
        String token;
        System.out.println(token = jwtBuilder.signWith(SignatureAlgorithm.HS256, "bla bla").compact());;
        Claims claims = (Claims)Jwts.parser().setSigningKey("bla bla").parse(token).getBody();
        System.out.println(claims.get("login", String.class));

    }

}
