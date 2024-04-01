package com.rodrigo.feedbacksystem.entity;

import com.rodrigo.feedbacksystem.enums.AreaTrabalho;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name="TB_FUNCIONARIO")
public class Funcionario {

    @Id
    @Column( unique = true )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String nome;

    @Column( nullable = false, unique = true)
    private String email;

    private LocalDate data_cadastro = LocalDate.now();;

    private Boolean ativo;


    @ElementCollection(fetch = FetchType.EAGER)  // quando der um get nessa AreasTRabalho o funcionario tem que vir junto
    @CollectionTable(name = "TB_AREATRABALHO")  // tera uma tabela no BD
    protected Set<Integer> areasTrabalho = new HashSet<>();  //Set nao permite dois valores igual / clientes iguais

    public Set<AreaTrabalho> getAreasTrabalho(){
        return areasTrabalho.stream().map(AreaTrabalho::toEnum).collect(Collectors.toSet()); // coleta tudo para o tipo Set<>
    }

    public void addAreasTrabalho(AreaTrabalho areaTrabalho){
        this.areasTrabalho.add(areaTrabalho.getCodigo());
    }

}
