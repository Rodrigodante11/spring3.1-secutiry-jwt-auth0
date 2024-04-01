package com.rodrigo.feedbacksystem.utils;

import com.rodrigo.feedbacksystem.dto.FuncionarioDTO;
import com.rodrigo.feedbacksystem.dto.RegisterDTO;
import com.rodrigo.feedbacksystem.entity.Funcionario;
import com.rodrigo.feedbacksystem.entity.User;
import com.rodrigo.feedbacksystem.dto.TokenDTO;
import com.rodrigo.feedbacksystem.enums.AreaTrabalho;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class Converter {

    public static Funcionario funcionario(FuncionarioDTO funcionarioDTO){
        Set<Integer> areasTrabalhoCodigos = funcionarioDTO.getAreasTrabalho().stream()
                .map(AreaTrabalho::getCodigo) // supondo que haja um método getCodigo() em AreaTrabalhoDTO
                .collect(Collectors.toSet());

        return Funcionario.builder()
                .nome(funcionarioDTO.getNome())
                .email(funcionarioDTO.getEmail())
                .data_cadastro(LocalDate.now())
                .ativo(funcionarioDTO.getAtivo())
                .areasTrabalho(areasTrabalhoCodigos)
                .build();
    }

    public static TokenDTO tokenDTO(User user , String token){
        return TokenDTO.builder()
                .nome(user.getNome())
                .token(token)
                .Role(user.getRole().name())
                .dataCriacao(user.getDataCriacao())
                .build();
    }

    public static User user(RegisterDTO registerDTO){
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.senha());
        return  User.builder()
                .nome(registerDTO.nome())
                .email(registerDTO.email())
                .role(registerDTO.role())
                .senha(encryptedPassword)
                .build();
    }
}
