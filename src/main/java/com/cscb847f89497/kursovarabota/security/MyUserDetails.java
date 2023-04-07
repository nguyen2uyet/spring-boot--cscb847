package com.cscb847f89497.kursovarabota.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cscb847f89497.kursovarabota.entity.Role;
import com.cscb847f89497.kursovarabota.entity.User;

public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    public boolean isStudent(){
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if(role.getName().equals("STUDENT")){
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(){
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if(role.getName().equals("ADMIN")){
                return true;
            }
        }
        return false;
    }

    public boolean isEditor(){
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if(role.getName().equals("EDITOR")){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.isEnabled();
    }

}
