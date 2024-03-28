package com.wiliantv.votacao.pauta.sessao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessaoRepository extends JpaRepository<Sessao, UUID> {

}
