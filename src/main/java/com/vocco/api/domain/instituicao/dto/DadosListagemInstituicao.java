package com.vocco.api.domain.instituicao.dto;

import com.vocco.api.domain.instituicao.Instituicao;

import java.math.BigDecimal;

public record DadosListagemInstituicao(
        Long id,
        String nome,
        String site,
        String sigla,
        BigDecimal notaMec
) {
    public DadosListagemInstituicao(Instituicao instituicao){
        this(instituicao.getId(), instituicao.getNome(), instituicao.getSigla(), instituicao.getSite(), instituicao.getNotaMec());
    }
}
