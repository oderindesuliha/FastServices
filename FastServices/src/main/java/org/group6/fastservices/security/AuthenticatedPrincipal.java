package org.group6.fastservices.security;

import lombok.Getter;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class AuthenticatedPrincipal implements UserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final Collection<SimpleGrantedAuthority> authorities;
    private final boolean isOrganization;

    public AuthenticatedPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getAuthorities().stream()
                .map(a -> (SimpleGrantedAuthority) a)
                .collect(Collectors.toList());
        this.isOrganization = false;
    }

    public AuthenticatedPrincipal(Organization org) {
        this.id = org.getId();
        this.username = org.getCode(); // IMPORTANT difference
        this.password = org.getPassword();
        this.authorities = org.getAuthorities().stream()
                .map(a -> (SimpleGrantedAuthority) a)
                .collect(Collectors.toList());
        this.isOrganization = true;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
