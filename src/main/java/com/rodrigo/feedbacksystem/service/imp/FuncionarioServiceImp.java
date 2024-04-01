package com.rodrigo.feedbacksystem.service.imp;

import com.rodrigo.feedbacksystem.entity.Funcionario;
import com.rodrigo.feedbacksystem.enums.AreaTrabalho;
import com.rodrigo.feedbacksystem.repository.FuncionarioRepository;
import com.rodrigo.feedbacksystem.service.FuncionarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioServiceImp implements FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImp(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario salvar(Funcionario funcionario) {
        Optional<Funcionario> funcionarioVericaEmailDuplicado = getByEmail(funcionario.getEmail());
        if(funcionarioVericaEmailDuplicado.isPresent()) throw new DataIntegrityViolationException("Email ja registrado No sistema");
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> obterPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    @Override
    public Funcionario atualizar(Funcionario funcionario) {
        Objects.requireNonNull(funcionario.getId());
        return funcionarioRepository.save(funcionario);

    }

    @Override
    public Optional<Funcionario> getByEmail(String email) {
        return funcionarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Funcionario> getByAreaTrabalho(AreaTrabalho areaTrabalho) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscar(Funcionario funcionarioFiltro) {
        Example<Funcionario> example = Example.of(funcionarioFiltro,
                ExampleMatcher.matching()
                        .withIgnoreCase()   // ignorar maisculo e minusculo
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        //.withStringMatcher(ExampleMatcher.StringMatcher.STARTING)); // acha todas com Inicio (rod)
        //.withStringMatcher(ExampleMatcher.StringMatcher.ENDING)); // acha todas com Fim (rod)
        //.withStringMatcher(ExampleMatcher.StringMatcher.EXACT)); // so acha (rodrigo) se escrever o nome inteiro

        return funcionarioRepository.findAll(example);
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }

    @Override
    public void deletar(Funcionario funcionario) {
        funcionarioRepository.delete(funcionario);
    }
}
