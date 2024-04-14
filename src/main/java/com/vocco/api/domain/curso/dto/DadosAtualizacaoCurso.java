package com.vocco.api.domain.curso.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCurso(
        @NotNull
        Long id,
        String descricao,
        Long areaId
) {
}
