package com.rodrigo.feedbacksystem.controllers;

import com.rodrigo.feedbacksystem.utils.Converter;
import com.rodrigo.feedbacksystem.dto.FuncionarioDTO;
import com.rodrigo.feedbacksystem.entity.Funcionario;
import com.rodrigo.feedbacksystem.exceptions.ErroFuncionarioException;
import com.rodrigo.feedbacksystem.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping()
    public ResponseEntity<?> salvarFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO){

        try{
            Funcionario funcionario = Converter.funcionario(funcionarioDTO);

            funcionario = funcionarioService.salvar(funcionario);
            return new ResponseEntity<>(funcionario, HttpStatus.CREATED);

        }catch (ErroFuncionarioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarFuncionario(@PathVariable("id") Long id ){
        return funcionarioService.obterPorId(id).map( funcionario -> {

            funcionarioService.deletar(funcionario);
            return new ResponseEntity<>("Funcionario "+ funcionario.getNome() +" Deletado Com Sucesso",
                    HttpStatus.NO_CONTENT);

        }).orElseGet( () ->
                new ResponseEntity<>("Funcionario nao encontrado na base de dados", HttpStatus.BAD_REQUEST)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> obterFuncionarioPorId(@PathVariable("id") Long id ){
        Optional<Funcionario> funcionario = funcionarioService.obterPorId(id);
        if (funcionario.isPresent())
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        return new ResponseEntity<>("Funcionario Nao encontrado",HttpStatus.NOT_FOUND);

    }
    @GetMapping()
    public ResponseEntity<?> obterFuncionarios(){
        List<Funcionario> funcionarios = funcionarioService.buscarTodos();
        if(funcionarios.isEmpty()) return new ResponseEntity<>("Funcionarios Nao encontrado",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);

    }
}
