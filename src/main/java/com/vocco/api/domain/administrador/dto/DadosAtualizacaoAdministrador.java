package com.vocco.api.domain.administrador.dto;

import com.vocco.api.domain.endereco.Endereco;

public record DadosAtualizacaoAdministrador(
        Long id,
        String nome,
        String cpf,
        String email,
        String cargo,
        String celular,
        Endereco endereco) {
}
