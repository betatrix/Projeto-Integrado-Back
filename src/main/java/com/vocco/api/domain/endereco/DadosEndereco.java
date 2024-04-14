package com.vocco.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(
        @NotBlank
        String cep,
        @NotBlank
        String logradouro,
        @NotBlank
        String estado,
        @NotBlank
        String cidade,
        @NotBlank
        String numero,
        String complemento) {
}
