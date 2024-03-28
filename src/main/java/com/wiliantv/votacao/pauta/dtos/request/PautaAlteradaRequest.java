package com.wiliantv.votacao.pauta.dtos.request;

import com.wiliantv.votacao.pauta.Pauta;
import lombok.Data;

@Data
public class PautaAlteradaRequest {
    private String descricao;
    private String proposta;

    public Pauta toEntity() {
        Pauta pauta= new Pauta();
        pauta.setDescricao(getDescricao());
        pauta.setProposta(getProposta());
        return pauta;

    }
}
