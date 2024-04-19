package com.vocco.api.domain.area.dto;

import com.vocco.api.domain.area.Area;

public record DadosDetalhamentoArea(
        String descricao,
        Boolean ativo
) {
    public DadosDetalhamentoArea(Area area){
        this(area.getDescricao(), area.getAtivo());

    }
}
