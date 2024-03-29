package com.wiliantv.votacao.pauta.sessao.votos;

import com.wiliantv.votacao.pauta.sessao.votos.request.VotosRequest;
import com.wiliantv.votacao.pauta.sessao.votos.response.VotosResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("api/pauta/sessao/votos")
public class VotosController {

    final VotosService service;

    public VotosController(VotosService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<VotosResponse> createPauta(@RequestBody VotosRequest pauta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(pauta));
    }

    @PutMapping("{id}")
    ResponseEntity<VotosResponse> updatePauta(@PathVariable UUID id, @RequestBody VotosRequest pauta) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, pauta));
    }

    @GetMapping("{id}")
    ResponseEntity<VotosResponse> getPauta(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getResponseById(id));
    }

    @GetMapping("")
    ResponseEntity<Page<VotosResponse>> getPauta(@ParameterObject Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }


    @DeleteMapping("{id}")
    ResponseEntity<Void> deletePauta(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
