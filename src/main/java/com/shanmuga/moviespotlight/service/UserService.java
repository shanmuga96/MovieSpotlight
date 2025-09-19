package com.shanmuga.moviespotlight.service;

import com.shanmuga.moviespotlight.entity.UserAccount;
import com.shanmuga.moviespotlight.model.dto.UserDto;
import com.shanmuga.moviespotlight.model.request.AuthRequest;
import com.shanmuga.moviespotlight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

//    private final JwtUtil jwtUtil;

    public UserAccount registerUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        UserAccount user = UserAccount.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(Set.of("ROLE_USER"))
                .build();

        return userRepository.save(user);
    }

    public String authenticate(AuthRequest authRequest) {
        UserAccount user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return "anonymousUser";
//        return jwtUtil.generateToken(user.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        );
    }
}
