package com.wiliantv.Votacao.pauta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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



}
