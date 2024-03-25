package com.wiliantv.Votacao.pauta.dtos.request;

import com.wiliantv.Votacao.pauta.Pauta;
import lombok.Data;

@Data
public class PautaAlteradaRequest {
    private String descricao;
    private String proposta;

    public Pauta toEntity(Pauta pauta) {
        pauta.setDescricao(getDescricao());
        pauta.setProposta(getProposta());
        return pauta;

    }
}
