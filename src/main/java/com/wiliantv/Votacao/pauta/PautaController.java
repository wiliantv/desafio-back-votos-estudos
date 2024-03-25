package com.wiliantv.Votacao.pauta;

import com.wiliantv.Votacao.pauta.dtos.request.PautaAlteradaRequest;
import com.wiliantv.Votacao.pauta.dtos.request.PautaRequest;
import com.wiliantv.Votacao.pauta.dtos.response.PautaResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pauta")
public class PautaController {

    PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    private ResponseEntity<PautaResponse> createPauta(@RequestBody PautaRequest pauta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.create(pauta));
    }
    @PutMapping("{id}")
    private ResponseEntity<PautaResponse> updatePauta(@RequestParam Long id,@RequestBody PautaAlteradaRequest pauta) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pautaService.update(id, pauta));
    }
    @GetMapping("{id}")
    private ResponseEntity<PautaResponse> getPauta(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findById(id));
    }
    @GetMapping("")
    private ResponseEntity<Page<PautaResponse>> getPauta(@ParameterObject Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findAll(pageable));
    }


    @DeleteMapping("{id}")
    private ResponseEntity deletePauta(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
