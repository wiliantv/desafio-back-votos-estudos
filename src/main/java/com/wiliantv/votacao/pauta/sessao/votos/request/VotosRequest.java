package com.wiliantv.votacao.pauta.sessao.votos.request;

import com.wiliantv.votacao.pauta.sessao.Sessao;
import com.wiliantv.votacao.pauta.sessao.votos.AlternativasEnum;
import com.wiliantv.votacao.pauta.sessao.votos.Votos;
import lombok.Data;

import java.util.UUID;

@Data
public class VotosRequest {

    private String cpf;
    private UUID idSessao;
    private AlternativasEnum voto;

    public Votos toEntity() {
        Votos votos = new Votos();
        votos.setCpf(cpf);
        votos.setSessao(new Sessao(idSessao));
        votos.setVoto(voto);
        return votos;
    }
}
