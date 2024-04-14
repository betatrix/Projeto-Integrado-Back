package com.vocco.api.domain.curso.dto;

import com.vocco.api.domain.curso.Curso;

public record DadosListagemCurso(
        Long id,
        String descricao
) {
    public DadosListagemCurso(Curso curso){
        this(curso.getId(), curso.getDescricao());
    }
}
