package com.vocco.api.domain.instituicao.dto;

import java.math.BigDecimal;

public record DadosCadastroInstituicao(
        String nome,
        String site,
        String sigla,
        BigDecimal notaMec
) {
}
