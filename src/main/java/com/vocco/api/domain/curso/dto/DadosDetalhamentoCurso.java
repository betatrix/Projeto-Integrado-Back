package com.vocco.api.domain.curso.dto;

import com.vocco.api.domain.area.dto.DadosListagemArea;
import com.vocco.api.domain.curso.Curso;
import com.vocco.api.domain.curso.NivelEmpregabilidade;

import java.util.List;

public record DadosDetalhamentoCurso(
        Long id,
        String descricao,
        Boolean ativo,
        DadosListagemArea area,
        NivelEmpregabilidade empregabilidade,
        List<String> possiveisCarreiras
) {
    public DadosDetalhamentoCurso(Curso curso){
        this(curso.getId(), curso.getDescricao(), curso.getAtivo(), new DadosListagemArea(curso.getArea()), curso.getEmpregabilidade(), curso.getPossiveisCarreiras());
    }
}
