package com.wiliantv.votacao.pauta.sessao.response;

import com.wiliantv.votacao.pauta.dtos.response.PautaResponse;
import com.wiliantv.votacao.pauta.sessao.Sessao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoResponse {

    private UUID id;
    private String nome;
    private PautaResponse pauta;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    public SessaoResponse(Sessao sessao) {
        this.id = sessao.getId();
        this.nome = sessao.getNome();
        this.pauta = new PautaResponse(sessao.getPauta());
        this.dataHoraInicio = sessao.getDataHoraInicio();
        this.dataHoraFim = sessao.getDataHoraFim();
    }
}
