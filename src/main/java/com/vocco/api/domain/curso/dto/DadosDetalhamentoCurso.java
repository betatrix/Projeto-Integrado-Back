package com.vocco.api.domain.curso.dto;

import com.vocco.api.domain.curso.Curso;

public record DadosDetalhamentoCurso(
        Long id,
        String descricao,
        Boolean ativo
//        DadosListagemArea area
) {
    public DadosDetalhamentoCurso(Curso curso){
        this(curso.getId(), curso.getDescricao(), curso.getAtivo());
    }
}
