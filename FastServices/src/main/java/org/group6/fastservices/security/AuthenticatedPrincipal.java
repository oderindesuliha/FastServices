package org.group6.fastservices.security;

import lombok.Getter;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AuthenticatedPrincipal implements UserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final boolean organizationAccount;

    public AuthenticatedPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorities = convertRoles(user.getRoles());
        this.organizationAccount = false;
    }

    public AuthenticatedPrincipal(Organization org) {
        this.id = org.getId();
        this.username = org.getCode(); // UNIQUE LOGIN FIELD
        this.password = org.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + org.getRole()));
        this.organizationAccount = true;
    }

    private List<GrantedAuthority> convertRoles(String rolesCommaSeparated) {
        return List.of(rolesCommaSeparated.split(","))
                .stream()
                .map(String::trim)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
