package com.mati.springschoolms.config.service.impl;

import com.mati.springschoolms.config.service.UserService;
import com.mati.springschoolms.entities.Profession;
import com.mati.springschoolms.entities.User;
import com.mati.springschoolms.repositories.ProfessionRepository;
import com.mati.springschoolms.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final PasswordEncoderServiceImpl passwordEncoder;
    private final UserRepository userRepository;
    private final ProfessionRepository professionRepository;

    public UserDetailsServiceImpl(PasswordEncoderServiceImpl passwordEncoder,
                                  UserRepository userRepository,
                                  ProfessionRepository professionRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.professionRepository = professionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        logger.info("User {} loaded!", username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getProfession().getName()))
        );
    }

    @Override
    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new BadCredentialsException("Invalid username or password");

        if (userRepository.existsByEmail(user.getEmail()))
            throw new BadCredentialsException("Invalid username or password");

        user.setPassword(passwordEncoder.passwordEncoder().encode(user.getPassword()));

        String professionName = user.getEmail().contains("admin.com") ? "Director" : "Other";

        Profession profession = professionRepository.findByName(professionName);

        user.setProfession(profession);

        logger.info("User {} with profession {} being registered...", user.getUsername(), user.getProfession().getName());

        return userRepository.save(user);
    }
}
