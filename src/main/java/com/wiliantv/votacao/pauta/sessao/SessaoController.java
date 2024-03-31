package com.wiliantv.votacao.pauta.sessao;

import com.wiliantv.votacao.exceptions.responses.ErrosValidation;
import com.wiliantv.votacao.pauta.sessao.request.SessaoRequest;
import com.wiliantv.votacao.pauta.sessao.response.SessaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

@Tag(name = "Sessoes")
@RestController
@RequestMapping("api/pauta/sessao")
public class SessaoController {

    final SessaoService service;

    public SessaoController(SessaoService service) {
        this.service = service;
    }

    @Operation(summary = "Criar uma nova sessão de votação",
            description = "Cria uma nova sessão de votação para uma determinada pauta.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para criar uma nova sessão de votação.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
                            @ExampleObject(name = "Data Predefinida", value = """
                                    {
                                      "nome": "Sessão de votação",
                                      "idPauta": 123,
                                      "dataHoraInicio": "2024-03-31T08:00:00",
                                      "dataHoraFim": "2024-03-31T18:00:00"
                                    }
                                    """),
                            @ExampleObject(name = "Tempo Predefinido", value = """
                                    {
                                      "nome": "Sessão de votação",
                                      "idPauta": 123,
                                      "dataHoraInicio": "2024-03-31T08:00:00",
                                      "tempoDuracao": 600
                                    }
                                    """),
                            @ExampleObject(name = "Sem Tempo Predefinido", value = """
                                    {
                                      "nome": "Sessão de votação",
                                      "idPauta": 123,
                                      "dataHoraInicio": "2024-03-31T08:00:00"
                                    }
                                    """)
                    })
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sessão criada com sucesso"),
                    @ApiResponse(responseCode = "422", description = "Erros na validação dos dados",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrosValidation.class))
                    ),
            }
    )
    @PostMapping
    ResponseEntity<SessaoResponse> create(@RequestBody SessaoRequest sessaoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(sessaoRequest));
    }

    @Operation(
            summary = "Atualizar uma sessão existente",
            description = "Atualiza uma sessão de votação que ainda não foi aberta para votação.",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Sessão atualizada com sucesso"),
                    @ApiResponse(responseCode = "422", description = "Erros na validação dos dados",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrosValidation.class))
                    ),
            }
    )
    @PutMapping("{id}")
    ResponseEntity<SessaoResponse> update(@PathVariable UUID id, @RequestBody SessaoRequest sessaoRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, sessaoRequest));
    }

    @Operation(summary = "Buscar uma sessão específica pelo seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sessão encontrada com sucesso")
            }
    )
    @GetMapping("{id}")
    ResponseEntity<SessaoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @Operation(summary = "Listar todas as sessões",
            description = "Lista todas as sessões de votação.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de sessões obtida com sucesso")
            }
    )
    @GetMapping("")
    ResponseEntity<Page<SessaoResponse>> findAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @Operation(summary = "Excluir uma sessão pelo seu ID",
            description = "Exclui uma sessão de votação pelo seu ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Sessão excluída com sucesso")
            }
    )
    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

