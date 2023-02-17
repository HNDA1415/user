package com.example.usertest.security;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwt(request);

        try {
            if (jwt != null && tokenProvider.validate(jwt)){

                String mobileNumber = tokenProvider.getMobileNumberFromToken(jwt);
                Claims claims = tokenProvider.getClaims(jwt);
                String roles = claims.get("roles", String.class);
                Integer userId = Integer.parseInt(claims.getId());
                List<String> authorityArray = Arrays.asList(roles.split(","));
                List<GrantedAuthority> authorities = authorityArray.stream()
                        .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
                UserPrincipal userPrincipal = new UserPrincipal(userId,null,mobileNumber,null, authorities);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userPrincipal, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
    private String getJwt(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")){
            return authHeader.replace("Bearer","");
        }
        return null;
    }
}
