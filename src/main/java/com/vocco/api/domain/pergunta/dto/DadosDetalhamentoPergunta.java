package com.vocco.api.domain.pergunta.dto;

import com.vocco.api.domain.pergunta.Pergunta;

public record DadosDetalhamentoPergunta(
        String texto,
        Boolean ativo
) {
    public DadosDetalhamentoPergunta(Pergunta pergunta){
        this(pergunta.getTexto(), pergunta.isAtivo());
    }
}
