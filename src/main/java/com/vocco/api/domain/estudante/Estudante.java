package com.vocco.api.domain.estudante;

import com.vocco.api.domain.estudante.dto.DadosAtualizacaoEstudante;
import com.vocco.api.domain.estudante.dto.DadosCadastroEstudante;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Consumer;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String celular;
    @Enumerated(EnumType.STRING)
    private NivelEscolar nivelEscolar;

    public Estudante(DadosCadastroEstudante dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.dataNascimento = dados.dataNascimento();
        this.celular = dados.celular();
        this.nivelEscolar = dados.nivelEscolar();
    }
    public void editarInformacoes(DadosAtualizacaoEstudante dados){
        atribuirSeNaoForNulo(dados.nome(), this::setNome);
        atribuirSeNaoForNulo(dados.email(), this::setEmail);
        atribuirSeNaoForNulo(dados.dataNascimento(), this::setDataNascimento);
        atribuirSeNaoForNulo(dados.celular(), this::setCelular);
        atribuirSeNaoForNulo(dados.nivelEscolar(), this::setNivelEscolar);
    }

    private <T> void atribuirSeNaoForNulo(T valor, Consumer<T> setter){
        if (Objects.nonNull(valor) && !valor.toString().trim().isEmpty()) {
            setter.accept(valor);
        }
    }

}
