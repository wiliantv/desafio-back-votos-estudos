package com.wiliantv.votacao.pauta;

import com.wiliantv.votacao.pauta.dtos.request.PautaAlteradaRequest;
import com.wiliantv.votacao.pauta.dtos.request.PautaRequest;
import com.wiliantv.votacao.pauta.dtos.response.PautaResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("api/pauta")
public class PautaController {

    final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    ResponseEntity<PautaResponse> createPauta(@RequestBody PautaRequest pauta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.create(pauta));
    }
    @PutMapping("{id}")
    ResponseEntity<PautaResponse> updatePauta(@PathVariable Long id,@RequestBody PautaAlteradaRequest pauta) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pautaService.update(id, pauta));
    }
    @GetMapping("{id}")
    ResponseEntity<PautaResponse> getPauta(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findById(id));
    }
    @GetMapping("")
    ResponseEntity<Page<PautaResponse>> getPauta(@ParameterObject Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findAll(pageable));
    }


    @DeleteMapping("{id}")
    ResponseEntity<Void> deletePauta(@PathVariable Long id) {
        pautaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
