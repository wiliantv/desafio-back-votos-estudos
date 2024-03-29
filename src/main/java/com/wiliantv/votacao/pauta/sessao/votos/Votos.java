package com.wiliantv.votacao.pauta.sessao.votos;

import com.wiliantv.votacao.pauta.Pauta;
import com.wiliantv.votacao.pauta.sessao.Sessao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Votos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @CPF
    @Column(nullable = false)
    private String cpf;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", nullable = false)
    @NotNull
    private Sessao sessao;

    @NotNull
    @Column
    private AlternativasEnum voto;



    public Votos(UUID id) {
        this.id = id;
    }


    public void updateChanges(Votos pauta) {
        this.voto = pauta.getVoto();
    }
}
