package com.wiliantv.votacao.pauta.dtos.response;

import com.wiliantv.votacao.pauta.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String proposta;

    public PautaResponse(Pauta pauta) {
        this.id = pauta.getId();
        this.titulo = pauta.getTitulo();
        this.descricao = pauta.getDescricao();
        this.proposta = pauta.getProposta();
    }
}
