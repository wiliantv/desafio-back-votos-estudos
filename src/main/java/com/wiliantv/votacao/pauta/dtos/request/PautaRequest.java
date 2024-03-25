package com.wiliantv.votacao.pauta.dtos.request;

import com.wiliantv.votacao.pauta.Pauta;
import lombok.Data;

@Data
public class PautaRequest {
    private String titulo;
    private String descricao;
    private String proposta;

    public Pauta toEntity() {
        Pauta pauta = new Pauta();
        pauta.setTitulo(getTitulo());
        pauta.setDescricao(getDescricao());
        pauta.setProposta(getProposta());
        return pauta;

    }
}
