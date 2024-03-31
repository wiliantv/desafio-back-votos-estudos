package com.wiliantv.votacao.pauta.sessao.votos;

import com.wiliantv.votacao.exceptions.responses.ErrosValidation;
import com.wiliantv.votacao.pauta.sessao.votos.request.VotosRequest;
import com.wiliantv.votacao.pauta.sessao.votos.response.VotosResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("ALL")

@Tag(name = "Votos")
@RestController
@RequestMapping("api/pauta/sessao/votos")
public class VotosController {

    final VotosService service;

    public VotosController(VotosService service) {
        this.service = service;
    }
    @Operation(summary = "Criar um novo voto",
            description = "Cria um novo voto com os dados fornecidos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Voto criado com sucesso"),
                    @ApiResponse(responseCode = "422", description = "Erros na validação dos dados",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrosValidation.class))
                    ),
            }
    )
    @PostMapping
    ResponseEntity<VotosResponse> createPauta(@RequestBody VotosRequest pauta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(pauta));
    }



    @Operation(summary = "Atualizar um voto existente",
            description = "Atualiza um voto existente com os dados fornecidos.",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Voto atualizado com sucesso"),
                    @ApiResponse(responseCode = "422", description = "Erros na validação dos dados",
                            content = @Content(
                                    schema = @Schema(implementation = ErrosValidation.class))
                    ),
            }
    )

    @PutMapping("{id}")
    ResponseEntity<VotosResponse> updatePauta(@PathVariable UUID id, @RequestBody VotosRequest pauta) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, pauta));
    }
    @Operation(summary = "Buscar um voto específico pelo seu ID",
            description = "Busca um voto existente com o ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Voto encontrado com sucesso"),
                    @ApiResponse(responseCode = "422", description = "Erros na validação dos dados",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrosValidation.class))
                    ),
            }
    )
    @GetMapping("{id}")
    ResponseEntity<VotosResponse> getPauta(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getResponseById(id));
    }
    @Operation(summary = "Listar todos os votos",
            description = "Lista todos os votos existentes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de votos obtida com sucesso",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
            }
    )

    @GetMapping("")
    ResponseEntity<Page<VotosResponse>> getPauta(@ParameterObject Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @Operation(summary = "Excluir um voto pelo seu ID",
            description = "Exclui um voto existente com o ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Voto excluído com sucesso"),
            }
    )
    @DeleteMapping("{id}")
    ResponseEntity<Void> deletePauta(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
