package com.rodrigo.feedbacksystem.service.imp;

import com.rodrigo.feedbacksystem.entity.User;
import com.rodrigo.feedbacksystem.repository.UserRepository;
import com.rodrigo.feedbacksystem.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void salvar(User user) {

        Optional<User> userVericaEmailDuplicado = buscarPorEmail(user.getEmail());
        if(userVericaEmailDuplicado.isPresent()) throw new DataIntegrityViolationException("Email ja registrado No sistema");
        userRepository.save(user);
    }
}
