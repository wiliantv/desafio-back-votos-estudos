package com.wiliantv.votacao.pauta.sessao.votos;

import com.wiliantv.votacao.exceptions.ObjectNotFoundException;
import com.wiliantv.votacao.pauta.sessao.Sessao;
import com.wiliantv.votacao.pauta.sessao.SessaoService;
import com.wiliantv.votacao.pauta.sessao.votos.request.VotosRequest;
import com.wiliantv.votacao.pauta.sessao.votos.response.VotosResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class VotosService {
    private final VotosRepository repository;
    private final SessaoService sessaoService;

    private final Validator validator;

    public VotosService(VotosRepository repository, SessaoService sessaoService, Validator validator) {
        this.repository = repository;
        this.sessaoService = sessaoService;
        this.validator = validator;
    }


    @Transactional
    public Votos create(Votos request) {
        validacoesComuns(request);

        return repository.save(request);
    }


    public VotosResponse create(VotosRequest request) {
        return new VotosResponse(create(request.toEntity()));
    }


    public Votos update(UUID id, Votos votos) {
        Votos existingVotos = getById(id);

        existingVotos.updateChanges(votos);
        validacoesComuns(existingVotos);
        return repository.save(existingVotos);
    }

    public VotosResponse update(UUID id, VotosRequest request) {
        return new VotosResponse(update(id, request.toEntity()));
    }
    private void validacoesComuns(Votos valida) {
        Set<ConstraintViolation<Votos>> validate = validator.validate(valida);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
        Sessao sessao = sessaoService.getById(valida.getSessao().getId());
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(sessao.getDataHoraInicio()) || now.isAfter(sessao.getDataHoraFim())){
            throw new IllegalStateException("A sessão associada ao voto não está aberta para votações.");
        }
        valida.setSessao(sessao);
    }


    public Votos getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Votos não encontrada"));
    }

    public VotosResponse getResponseById(UUID id) {
        Votos votos = getById(id);
        return new VotosResponse(votos);
    }

    public Page<VotosResponse> findAll(Pageable pageable) {
        Page<Votos> sessions = repository.findAll(pageable);
        return sessions.map(VotosResponse::new);
    }

    public void delete(UUID id) {
        Votos entity = getById(id);
        repository.delete(entity);
    }
}
