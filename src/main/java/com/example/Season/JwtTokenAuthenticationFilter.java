package com.example.Season;

import com.example.Season.entity.User2;
import com.example.Season.repository.User2Repository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    User2Repository userRepo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("CUSTOM FILTER START");
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authHeader);

        var a = request.getCookies(); // TODO !! NotWork
        System.out.println("request.getCookies()="+a);
        var cookies = Arrays.stream(request.getHeader(HttpHeaders.COOKIE).split(",")).map(e -> e.split("=")).collect(Collectors.toMap(e->e[0], e->e[1]));
        System.out.println("request.getHeader()="+cookies);

        String refresh_token = cookies.get("refresh_token");
        System.out.println("refresh_token="+refresh_token);

        if(authHeader != null) {
            var reg = Pattern.compile("([Bb]earer) ?(.*)").matcher(authHeader);
            System.out.println(reg.find());
            System.out.println(reg.group(2));

            var tk = Jwts.parser().verifyWith(Keys.hmacShaKeyFor("ruhqsufgquigqiugiuhfgruhqsufgquigqiugiuhfgruhqsufgquigqiugiuhfgruhqsufgquigqiugiuhfg".getBytes())).build().parseSignedClaims(reg.group(2)).getPayload();
            System.out.println(tk);
            SecurityContextHolder h = new SecurityContextHolder();
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            var user = userRepo.findByUserName(tk.getSubject());
            if (user != null) {
                context.setAuthentication(new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                        ));
                SecurityContextHolder.setContext(context);
            }
        }
        System.out.println("CUSTOM FILTER END");
        filterChain.doFilter(request, response);
    }
}
