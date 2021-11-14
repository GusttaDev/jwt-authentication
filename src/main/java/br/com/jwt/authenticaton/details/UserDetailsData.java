package br.com.jwt.authenticaton.details;

import br.com.jwt.authenticaton.model.UserAuthentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserDetailsData implements UserDetails {

    private final Optional<UserAuthentication> userAuthentication;

    public UserDetailsData(Optional<UserAuthentication> userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return userAuthentication.orElseGet(UserAuthentication::new).getPassword();
    }

    @Override
    public String getUsername() {
        return userAuthentication.orElseGet(UserAuthentication::new).getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
