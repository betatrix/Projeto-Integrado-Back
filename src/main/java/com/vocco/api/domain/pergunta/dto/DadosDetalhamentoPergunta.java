package com.vocco.api.domain.pergunta.dto;

import com.vocco.api.domain.perfil.dto.DadosListagemPerfil;
import com.vocco.api.domain.pergunta.Pergunta;

public record DadosDetalhamentoPergunta(
        String texto,
        String textoIngles,
        Boolean ativo,
        DadosListagemPerfil perfil
) {
    public DadosDetalhamentoPergunta(Pergunta pergunta){
        this(pergunta.getTexto(), pergunta.getTextoIngles(), pergunta.isAtivo(), new DadosListagemPerfil(pergunta.getPerfil()));
    }
}
