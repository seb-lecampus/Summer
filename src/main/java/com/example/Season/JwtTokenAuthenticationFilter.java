package com.example.Season;

import com.example.Season.entity.User2;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("CUSTOM FILTER START");
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authHeader);
        if(authHeader != null) {
            String[] authorization = authHeader.split("([Bb]earer) ?(.*)");
            System.out.println(authorization);

            SecurityContextHolder h = new SecurityContextHolder();
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(
                    new User2(),
                    null,
                    List.of(new GrantedAuthority() {
                        @Override
                        public String getAuthority() {
                            return "LOLILOL";
                        }
                    })));
            SecurityContextHolder.setContext(context);
        }
        System.out.println("CUSTOM FILTER END");
        filterChain.doFilter(request, response);
    }
}
