package com.example.Season.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name="jwtUser")
public class User2 implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String userName;
    private String password;
    private Boolean isEnabled;
    private Boolean iscredentialsExpired;
    private Boolean isAccountLocked;
    private Boolean isAccountExpired;

    private String authorities;

    public User2() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User2(String s, String password) {
        userName=s;
        this.password=password;
        isEnabled = true;
        iscredentialsExpired = false;
        isAccountExpired = false;
        isAccountLocked = false;
        authorities = "ROLE_USER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(";")).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !iscredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
