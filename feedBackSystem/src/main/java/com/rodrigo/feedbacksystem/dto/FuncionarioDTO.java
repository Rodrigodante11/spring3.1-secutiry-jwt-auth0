package com.rodrigo.feedbacksystem.dto;

import com.rodrigo.feedbacksystem.enums.AreaTrabalho;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {

    private Long id;
    @NotNull(message = "O campo Nome é Obrigatorio ")
    private String nome;
    @NotNull(message = "O campo EMAIL é Obrigatorio ")
    private String email;
    @NotNull(message = "O campo Ativo é Obrigatorio ")
    private Boolean ativo;

    private final LocalDate data_cadastro = LocalDate.now();;
    protected Set<Integer> areasTrabalho = new HashSet<>();  //Set nao permite dois valores igual / clientes iguais

    public Set<AreaTrabalho> getAreasTrabalho(){
        return areasTrabalho.stream().map(AreaTrabalho::toEnum).collect(Collectors.toSet());
    }

    public void addAreasTrabalho(AreaTrabalho areasTrabalho){
        this.areasTrabalho.add(areasTrabalho.getCodigo());
    }

}
