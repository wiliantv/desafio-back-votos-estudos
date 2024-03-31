package com.wiliantv.votacao.pauta.sessao.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.wiliantv.votacao.pauta.Pauta;
import com.wiliantv.votacao.pauta.sessao.Sessao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@SuppressWarnings("unused")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessaoRequest {
    @Schema(description = "Nome da sessao de votação")
    @NotNull
    private String nome;

    @Schema(description = "Id da pauta de uma sessao de votação")
    @NotNull
    private Long idPauta;

    @Schema(description = "Data e hora do inicio da sessao")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NotNull
    private LocalDateTime dataHoraInicio;

    @Schema(description = "Data e hora do fim da sessao")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraFim;

    @Schema(description = "Tempo em minutos que dura a sessao")
    private Long tempoDuracao;

    public Sessao toEntity() {
        return this.toEntity(new Sessao());
    }
    public Sessao toEntity(Sessao sessao) {
        sessao.setNome(nome);
        sessao.setPauta(new Pauta(idPauta));
        sessao.setDataHoraInicio(dataHoraInicio);
        // Se dataHoraFim não foi informado, calcula-se a partir da dataHoraInicio e tempoDuracao
        if (getDataHoraFim() == null) {
            sessao.setDataHoraFim(getDataHoraInicio().plusMinutes(getTempoDuracao() < 0 ? 1: getTempoDuracao() ));
        }else{
            sessao.setDataHoraFim(getDataHoraFim());
        }
        return sessao;
    }


    public static String getJsonExemple(){
        return """
                {
                  "nome": "Sessão de votação",
                  "idPauta": 123,
                  "dataHoraInicio": {now},
                  "dataHoraFim": {now},
                  "tempoDuracao": 600
                }
                """.replace("{now}", LocalDateTime.now().toString());
    }
}
