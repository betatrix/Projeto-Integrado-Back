package com.vocco.api.domain.administrador.dto;

import com.vocco.api.domain.administrador.Administrador;
import com.vocco.api.domain.endereco.Endereco;


public record DadosDetalhamentoAdministrador(
        Long id,
        String nome,
        String cpf,
        String email,
        String cargo,
        String celular,
        Endereco endereco) {
    public DadosDetalhamentoAdministrador(Administrador administrador){
        this(administrador.getId(), administrador.getNome(), administrador.getCpf(), administrador.getEmail(), administrador.getCargo(), administrador.getCelular(), administrador.getEndereco());
    }
}
