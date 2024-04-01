package com.rodrigo.feedbacksystem.service;

import com.rodrigo.feedbacksystem.entity.Funcionario;
import com.rodrigo.feedbacksystem.enums.AreaTrabalho;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

    Funcionario salvar(Funcionario funcionario);

    Optional<Funcionario> obterPorId(Long id);

    Funcionario atualizar(Funcionario funcionario);

    Optional<Funcionario> getByEmail(String email);

    Optional<Funcionario> getByAreaTrabalho(AreaTrabalho areaTrabalho);

    List<Funcionario> buscar(Funcionario funcionario);

    List<Funcionario> buscarTodos();

    void deletar(Funcionario funcionario);

}
