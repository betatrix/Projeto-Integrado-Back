package com.vocco.api.domain.instituicao;

import com.vocco.api.domain.instituicao.dto.DadosAtualizacaoInstituicao;
import com.vocco.api.domain.instituicao.dto.DadosCadastroInstituicao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Consumer;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String site;
    private BigDecimal notaMec;
    private String sigla;
    //Falta endereco

    public Instituicao(DadosCadastroInstituicao dados){
        this.nome = dados.nome();
        this.site = dados.site();
        this.notaMec = dados.notaMec();
        this.sigla = dados.sigla();
    }

    public void editarInformacoes(DadosAtualizacaoInstituicao dados){
        atribuirSeForNaoNulo(dados.nome(), this::setNome);
        atribuirSeForNaoNulo(dados.site(), this::setSite);
        atribuirSeForNaoNulo(dados.notaMec(), this::setNotaMec);
        atribuirSeForNaoNulo(dados.sigla(), this::setSigla);
    }

    private <T> void atribuirSeForNaoNulo(T valor, Consumer<T> setter) {
        if (Objects.nonNull(valor) && !valor.toString().trim().isEmpty()) {
            setter.accept(valor);
        }
    }
}
