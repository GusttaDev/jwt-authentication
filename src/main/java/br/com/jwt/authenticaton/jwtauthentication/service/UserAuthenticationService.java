package br.com.jwt.authenticaton.jwtauthentication.service;

import br.com.jwt.authenticaton.jwtauthentication.config.PasswordEncoderConfig;
import br.com.jwt.authenticaton.jwtauthentication.model.UserAuthentication;
import br.com.jwt.authenticaton.jwtauthentication.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAuthenticationService {

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private PasswordEncoderConfig passwordEncoder;

    public List<UserAuthentication> findAll(){
        return userAuthenticationRepository.findAll();
    }

    public UserAuthentication create(UserAuthentication user){
        return userAuthenticationRepository.save(user);
    }

    /** Criado validação, caso o userDB não exista no banco vai retornar false e HttpStatus.UNAUTHORIZED
     * Caso o userDB exista, será dado um matches com o password vindo da request e o password encontrado na base de dado, se existir retorna true e HttpStatus.OK
     * Se os password forem diferente retorna false e HttpStatus.UNAUTHORIZED*/
    public HttpStatus validatePassword(String login, String password){
        Optional<UserAuthentication> userDB = userAuthenticationRepository.findByLogin(login);

        boolean valid = userDB.filter(userAuthentication -> passwordEncoder.getPasswordEncoder().matches(password, userAuthentication.getPassword())).isPresent();

        return valid ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
    }

}
