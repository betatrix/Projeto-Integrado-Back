package com.vocco.api.domain.instituicao.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record DadosCadastroInstituicao(
        @NotBlank
        String nome,
        @NotBlank
        String site,
        @NotBlank
        String sigla,
        BigDecimal notaMec
) {
}
