package com.vocco.api.domain.instituicao.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizacaoInstituicao(
        @NotNull
        Long id,
        String nome,
        String site,
        BigDecimal notaMec,
        String sigla) {
}
