package com.shanmuga.moviespotlight.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // store hashed password

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles; // e.g., ROLE_USER, ROLE_ADMIN
}
