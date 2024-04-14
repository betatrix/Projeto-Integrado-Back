package com.vocco.api.domain.curso;

import com.vocco.api.domain.curso.dto.DadosAtualizacaoCurso;
import com.vocco.api.domain.curso.dto.DadosCadastroCurso;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.geom.Area;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Boolean ativo;
    private String empregabilidade; // Necessário verificar
    private List<String> possiveisCarreiras; //Talvez transformar carreira em uma entidade
//    @ManyToOne
//    private Area area;

    public Curso(DadosCadastroCurso dados){
        this.descricao = dados.descricao();
        this.ativo = true;
    }

    public void excluir(){
        this.ativo = false;
    }
    public void editarInformacoes(DadosAtualizacaoCurso dados){
        atribuirSeForNaoNulo(dados.descricao(), this::setDescricao);
    }

    private <T> void atribuirSeForNaoNulo(T valor, Consumer<T> setter) {
        if (Objects.nonNull(valor) && !valor.toString().trim().isEmpty()) {
            setter.accept(valor);
        }
    }

    public boolean isAtivo() {
        return ativo;
    }
}
