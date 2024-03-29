package ru.nazarfatichov.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        Optional<Token> tokenOptional = tokenRepository.findOneByValue(tokenAuthentication.getName());

        if(tokenOptional.isPresent()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenOptional.get().getUser().getLogin());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } throw new IllegalArgumentException("Bad token");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
