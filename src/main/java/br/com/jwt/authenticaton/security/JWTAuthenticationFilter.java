package br.com.jwt.authenticaton.security;

import br.com.jwt.authenticaton.details.UserDetailsData;
import br.com.jwt.authenticaton.model.UserAuthentication;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 86400000;
    public static final String TOKEN_SECRET = "6d602198-44ff-11ec-81d3-0242ac130003";

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UserAuthentication user = new ObjectMapper().readValue(request.getInputStream(), UserAuthentication.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to authenticate user!", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsData userDetailsData = (UserDetailsData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(userDetailsData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_SECRET));

        response.getWriter().write(token);
        response.getWriter().flush();

    }
}
