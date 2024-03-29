package com.wiliantv.votacao.pauta.sessao.votos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotosRepository extends JpaRepository<Votos, UUID> {

}
