package ru.nazarfatichov.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.nazarfatichov.model.Token;
import ru.nazarfatichov.repositories.TokenRepository;
import ru.nazarfatichov.security.token.TokenAuthentication;

import java.util.Optional;

@Component
public class JwtAuthProvider implements AuthenticationProvider {

    @Value("${key}")
    private String key;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        Claims claims = (Claims) Jwts.parser().setSigningKey(key).parse(authentication.getName()).getBody();

        if(!claims.get("login", String.class).isEmpty()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(claims.get("login", String.class));
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } throw new IllegalArgumentException("Bad token");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
