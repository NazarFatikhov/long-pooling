package ru.nazarfatichov.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.model.User;
import ru.nazarfatichov.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findOneByLogin(s);
        if(userOptional.isPresent()) {
            return new UserDetailsImpl(userOptional.get());
        } else {
            throw new UsernameNotFoundException("User with login " + s + " not found");
        }
    }
}
