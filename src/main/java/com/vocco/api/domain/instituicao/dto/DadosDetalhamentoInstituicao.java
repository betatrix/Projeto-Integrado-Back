package com.vocco.api.domain.instituicao.dto;

import com.vocco.api.domain.instituicao.Instituicao;

import java.math.BigDecimal;

public record DadosDetalhamentoInstituicao(
        Long id,
        String nome,
        String sigla,
        String site,
        BigDecimal notaMec,
        Boolean ativo
        //falta endereco
) {
    public DadosDetalhamentoInstituicao(Instituicao instituicao){
        this(instituicao.getId(), instituicao.getNome(), instituicao.getSigla(), instituicao.getSite(), instituicao.getNotaMec(), instituicao.getAtivo());
    }
}
