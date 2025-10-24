package org.group6.fastservices.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String phone;

    @NotBlank
    private String roles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setRoles(Set<Role> roleSet) {
        this.roles = String.join(",",
                roleSet.stream().map(Enum::name).toList()
        );
    }

    public Set<Role> getRolesAsSet() {
        if (roles == null || roles.isEmpty()) return new HashSet<>();
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles.split(",")) {
            try {
                roleSet.add(Role.valueOf(role.trim()));
            } catch (IllegalArgumentException ignored) {}
        }
        return roleSet;
    }
}
