package br.com.jwt.authenticaton.service;

import br.com.jwt.authenticaton.details.UserDetailsData;
import br.com.jwt.authenticaton.model.UserAuthentication;
import br.com.jwt.authenticaton.repository.UserAuthenticationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAuthenticationRepository repository;

    public UserDetailsServiceImpl(UserAuthenticationRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAuthentication> userDB = repository.findByLogin(username);

        if(userDB.isEmpty()){
            throw new UsernameNotFoundException("User ["+username+"] not found!");
        }

        return new UserDetailsData(userDB);
    }
}
