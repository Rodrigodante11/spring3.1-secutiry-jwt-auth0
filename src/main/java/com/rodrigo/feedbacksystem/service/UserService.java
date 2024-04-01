package com.rodrigo.feedbacksystem.service;


import com.rodrigo.feedbacksystem.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> buscarPorEmail(String email);

    void salvar(User user);


}
