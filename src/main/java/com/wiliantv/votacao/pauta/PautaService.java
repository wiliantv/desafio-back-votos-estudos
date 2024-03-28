package com.wiliantv.votacao.pauta;

import com.wiliantv.votacao.exceptions.ObjectNotFoundException;
import com.wiliantv.votacao.pauta.dtos.request.PautaAlteradaRequest;
import com.wiliantv.votacao.pauta.dtos.request.PautaRequest;
import com.wiliantv.votacao.pauta.dtos.response.PautaResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PautaService {
    private final PautaRepository repository;
    private final Validator validator;

    public PautaService(PautaRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public PautaResponse create(PautaRequest pautaRequest) {
        Pauta saved = save(pautaRequest.toEntity());
        return new PautaResponse(saved);
    }

    public PautaResponse update(Long id, PautaAlteradaRequest pautaRequest) {
        return new PautaResponse(update(id, pautaRequest.toEntity()));
    }

    public Pauta update(Long id, Pauta pautaRequest) {
        Pauta pauta = getById(id);
        pauta.updateChanges(pautaRequest);
        return save(pauta);
    }


    public Page<PautaResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(PautaResponse::new);
    }

    private Pauta save(Pauta pauta) {
        Set<ConstraintViolation<Pauta>> validate = validator.validate(pauta);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException("Erro o validar os campos", validate);
        }
        return repository.save(pauta);
    }

    public Pauta getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pauta não encontrada"));
    }

    public PautaResponse findById(Long id) {
        return new PautaResponse(getById(id));
    }

    public void delete(Long id) {
        Pauta entity = getById(id);
        repository.delete(entity);
    }
}
