package com.example.demo.service;

import com.example.demo.model.UserEntity;

public interface UserService {

    UserEntity create(final UserEntity userEntity);

    UserEntity getByCredentials(final String email, final String password);
}
