package com.wiliantv.votacao.pauta.sessao;

import com.wiliantv.votacao.pauta.Pauta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    @NotNull
    private Pauta pauta;
    @Column(nullable = false)
    @NotNull
    private LocalDateTime dataHoraInicio;
    @Column(nullable = false)
    @NotNull
    private LocalDateTime dataHoraFim;


    public Sessao(UUID id) {
        this.id = id;
    }


    public void updateChanges(Sessao pauta) {
        this.nome = pauta.getNome();
        this.dataHoraInicio = pauta.getDataHoraInicio();
        this.dataHoraFim = pauta.getDataHoraFim();
    }
}
