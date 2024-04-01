package com.rodrigo.feedbacksystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TokenDTO {

    private String nome;
    private String token;
    private String Role;
    private LocalDate dataCriacao;
}
