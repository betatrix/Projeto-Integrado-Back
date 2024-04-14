package com.vocco.api.domain.curso.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(
        @NotBlank
        String descricao
) {
}
