package com.example.demo.service;

import com.example.demo.model.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    UserEntity create(final UserEntity userEntity);

    UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder);
}
