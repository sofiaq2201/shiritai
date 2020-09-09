package com.sofiaq.shiritai.security.model;
//encargada de generar la seguridad
//privilegios de usuario

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMain implements UserDetails{
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserMain(String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserMain build(User user){
        //convertimos y construimos los roles de user en GrantedAuthority
        List<GrantedAuthority> authorities = 
                user.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(
                                rol.getNombre().name()))
                        .collect(Collectors.toList()); 
        
        return new UserMain(user.getUsername(),user.getEmail(),user.getPassword(), authorities);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public String getEmail() {
        return email;
    }
    
    
}
