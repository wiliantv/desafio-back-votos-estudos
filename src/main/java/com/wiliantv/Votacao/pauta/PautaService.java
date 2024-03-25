package com.wiliantv.Votacao.pauta;

import com.wiliantv.Votacao.pauta.dtos.request.PautaAlteradaRequest;
import com.wiliantv.Votacao.pauta.dtos.request.PautaRequest;
import com.wiliantv.Votacao.pauta.dtos.response.PautaResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class PautaService {
    private PautaRepository repository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public PautaService(PautaRepository repository) {
        this.repository = repository;
    }

    public PautaResponse create(PautaRequest pautaRequest) {
        Pauta saved = save(pautaRequest.toEntity());
        return new PautaResponse(saved);
    }

    public PautaResponse update(Long id, PautaAlteradaRequest pautaRequest) {

        Pauta pauta = getById(id);
        pautaRequest.toEntity(pauta);
        return new PautaResponse(save(pauta));
    }



    public Page<PautaResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(PautaResponse::new);
    }

    public Pauta save(Pauta pauta) {
        Set<ConstraintViolation<Pauta>> validate = validator.validate(pauta);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
        return repository.save(pauta);
    }

    public Pauta getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pauta não encontrada"));
    }
    public PautaResponse findById(Long id) {
        return new PautaResponse(getById(id));
    }
}
