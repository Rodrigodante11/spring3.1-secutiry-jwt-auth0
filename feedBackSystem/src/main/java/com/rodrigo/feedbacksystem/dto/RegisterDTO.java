package com.rodrigo.feedbacksystem.dto;

import com.rodrigo.feedbacksystem.enums.UserRole;

public record RegisterDTO(String nome, String email, String senha, UserRole role) {
}
