package org.group6.fastservices.data.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "admins")
@DiscriminatorValue("Admin")
public class Admin extends User {
    private String adminLevel;
    private String department;
}

