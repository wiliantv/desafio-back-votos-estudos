package com.wiliantv.votacao.pauta.sessao;

import com.wiliantv.votacao.exceptions.Generic4XXException;
import com.wiliantv.votacao.exceptions.ObjectNotFoundException;
import com.wiliantv.votacao.pauta.sessao.request.SessaoRequest;
import com.wiliantv.votacao.pauta.sessao.response.SessaoResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class SessaoService {
    private final SessaoRepository repository;
    private final Validator validator;

    public SessaoService(SessaoRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Transactional
    @Transient
    public Sessao create(Sessao request) {
        validacoesComuns(request);

        return repository.save(request);
    }


    public SessaoResponse create(SessaoRequest request) {
        return new SessaoResponse(create(request.toEntity()));
    }


    public Sessao update(UUID id, Sessao sessao) {
        Sessao existingSessao = getById(id);
        if (existingSessao.getDataHoraFim().isAfter(LocalDateTime.now())) {
            throw new Generic4XXException("Sessão já finalizada, não pode ser alterada");
        }
        if (existingSessao.getDataHoraInicio().isAfter(LocalDateTime.now())) {
            throw new Generic4XXException("Sessão já iniciada, não pode ser alterada");
        }
        existingSessao.updateChanges(sessao);
        validacoesComuns(existingSessao);
        return repository.save(existingSessao);
    }

    public SessaoResponse update(UUID id, SessaoRequest request) {
        return new SessaoResponse(update(id, request.toEntity()));
    }
    private void validacoesComuns(Sessao valida) {
        Set<ConstraintViolation<Sessao>> validate = validator.validate(valida);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
        if (valida.getDataHoraInicio() == null) {
            valida.setDataHoraInicio(LocalDateTime.now());
        }
        if (valida.getDataHoraFim() == null) {
            valida.setDataHoraInicio(LocalDateTime.now().plusMinutes(1));
        }
        if (valida.getDataHoraInicio().isAfter(valida.getDataHoraInicio())) {
            throw new Generic4XXException("O fim da Sessão não pode ser antes do inicio");
        }
    }


    public Sessao getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Sessao não encontrada"));
    }

    public SessaoResponse findById(UUID id) {
        Sessao sessao = getById(id);
        return new SessaoResponse(sessao);
    }

    public Page<SessaoResponse> findAll(Pageable pageable) {
        Page<Sessao> sessions = repository.findAll(pageable);
        return sessions.map(SessaoResponse::new);
    }

    public void delete(UUID id) {
        Sessao entity = getById(id);
        repository.delete(entity);
    }
}
