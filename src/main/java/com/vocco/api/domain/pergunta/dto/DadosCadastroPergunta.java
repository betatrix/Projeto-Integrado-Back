package com.vocco.api.domain.pergunta.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroPergunta(
        @NotNull
        String texto
) {
}
