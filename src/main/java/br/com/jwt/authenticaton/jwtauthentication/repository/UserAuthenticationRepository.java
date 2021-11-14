package br.com.jwt.authenticaton.jwtauthentication.repository;

import br.com.jwt.authenticaton.jwtauthentication.model.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {

    Optional<UserAuthentication> findByLogin(String login);
}
