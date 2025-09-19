package com.shanmuga.moviespotlight.repository;

import com.shanmuga.moviespotlight.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, BigInteger> {
    Optional<UserAccount> findByUsername(String username);
}
