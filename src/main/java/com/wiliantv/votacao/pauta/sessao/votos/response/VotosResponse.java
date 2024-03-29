package com.wiliantv.votacao.pauta.sessao.votos.response;

import com.wiliantv.votacao.pauta.sessao.response.SessaoResponse;
import com.wiliantv.votacao.pauta.sessao.votos.AlternativasEnum;
import com.wiliantv.votacao.pauta.sessao.votos.Votos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotosResponse {
    private UUID id;
    private String cpf;
    private SessaoResponse sessao;
    private AlternativasEnum voto;

    public VotosResponse(Votos id) {
        this.id = id.getId();
        this.cpf = id.getCpf();
        this.sessao = new SessaoResponse(id.getSessao());
        this.voto = id.getVoto();

    }
}
