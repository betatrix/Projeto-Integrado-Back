package com.vocco.api.domain.teste.dto;

import com.vocco.api.domain.teste.Teste;

public record DadosDetalhamentoTeste(
        String titulo,
        String descricao
) {
    public DadosDetalhamentoTeste(Teste teste){
        this(teste.getTitulo(), teste.getDescricao());
    }
}
