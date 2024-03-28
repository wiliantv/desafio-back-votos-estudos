package com.wiliantv.votacao.pauta.sessao.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.wiliantv.votacao.pauta.Pauta;
import com.wiliantv.votacao.pauta.sessao.Sessao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@SuppressWarnings("unused")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessaoRequest {
    private String nome;
    private Long idPauta;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraInicio;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraFim;
    private Long tempoDuracao;

    public Sessao toEntity() {
        return this.toEntity(new Sessao());
    }
    public Sessao toEntity(Sessao sessao) {
        sessao.setNome(nome);
        sessao.setPauta(new Pauta(idPauta));
        sessao.setDataHoraInicio(dataHoraInicio);
        sessao.setDataHoraFim(dataHoraFim);
        return sessao;
    }
}
