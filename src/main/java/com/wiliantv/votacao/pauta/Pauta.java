package com.wiliantv.votacao.pauta;

import com.wiliantv.votacao.pauta.sessao.Sessao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 3, max = 150)
    @Column(nullable = false, unique = true)
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @NotNull @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String proposta;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Sessao.class)
    @JoinColumn(name = "pauta_id")
    private List<Sessao> sessoes;

    public Pauta(Long id) {
        this.id = id;
    }

    public void updateChanges(Pauta pauta) {
        this.descricao = pauta.getDescricao();
        this.proposta = pauta.getProposta();
    }
}
