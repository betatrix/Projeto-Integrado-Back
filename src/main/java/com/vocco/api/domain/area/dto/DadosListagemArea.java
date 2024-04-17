package com.vocco.api.domain.area.dto;

import com.vocco.api.domain.area.Area;

public record DadosListagemArea(
        String descricao
) {
    public DadosListagemArea(Area area){
        this(area.getDescricao());
    }
}
