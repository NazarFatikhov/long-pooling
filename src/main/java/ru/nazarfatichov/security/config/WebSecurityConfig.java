package ru.nazarfatichov.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.nazarfatichov.security.filter.TokenAuthFilter;

@ComponentScan("ru.nazarfatichov")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("jwtAuthProvider")
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers("/signIn").permitAll()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/chat").authenticated()
                .antMatchers("/messages").authenticated();
        http.csrf().disable();
    }

}
