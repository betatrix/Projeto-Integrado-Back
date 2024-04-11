package com.vocco.api.domain.instituicao.dto;

import java.math.BigDecimal;

public record DadosAtualizacaoInstituicao(
        Long id,
        String nome,
        String site,
        BigDecimal notaMec,
        String sigla
) {
}
