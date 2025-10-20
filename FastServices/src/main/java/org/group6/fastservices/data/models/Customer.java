package org.group6.fastservices.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name ="customers")
public class Customer extends User {

    private String contact;
    private String address;
    private String gender;
}
