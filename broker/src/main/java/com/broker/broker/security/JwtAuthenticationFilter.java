package com.broker.broker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.broker.broker.domain.UserBroker;
import com.broker.broker.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        try{
            UserBroker creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserBroker.class);
            System.out.println(creds.toString());
           // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            //System.out.println(encoder.encode(creds.getPassword()));

            return authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>())
            );

        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String roles ="";
        int i = 0;
        int len = ((User)auth.getPrincipal()).getAuthorities().size();
        for(GrantedAuthority g: ((User)auth.getPrincipal()).getAuthorities())
        {
            if(i == len-1)
            {
                roles += g.getAuthority();
                break;
            }
            roles += g.getAuthority() + ",";
        }

        String token = JWT.create()
                .withSubject(((User)auth.getPrincipal()).getUsername())
                .withClaim("roles", roles )
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(Constants.SECRET_KEY.getBytes()));

        res.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX + token);


    }
}
