package com.rodrigo.feedbacksystem.service.imp;



import com.rodrigo.feedbacksystem.entity.User;
import com.rodrigo.feedbacksystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationServiceImp implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);

        if(user.isPresent()){
            return new User(user.get().getNome(), user.get().getEmail(), user.get().getSenha(), user.get().getRole());
        }

        throw new UsernameNotFoundException(email);
    }
}
