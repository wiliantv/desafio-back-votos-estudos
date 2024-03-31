package com.wiliantv.votacao.pauta;

import com.wiliantv.votacao.exceptions.responses.ErrosValidation;
import com.wiliantv.votacao.pauta.dtos.request.PautaAlteradaRequest;
import com.wiliantv.votacao.pauta.dtos.request.PautaRequest;
import com.wiliantv.votacao.pauta.dtos.response.PautaResponse;
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


@Tag(name = "Pautas")
@RestController
@RequestMapping("api/pauta")
public class PautaController {

    final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @Operation(summary = "Criar uma nova pauta",
            description = "Cria uma nova pauta com os dados fornecidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para criar uma nova pauta",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "Completa", value = """
                                    {
                                      "titulo": "Título da pauta",
                                      "descricao": "Descrição da pauta",
                                      "proposta": "Proposta da pauta"
                                    }
                                    """)
                    })
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pauta criada com sucesso"),
                    @ApiResponse(responseCode = "422", description = "Erros na validação dos dados",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrosValidation.class))
                    ),
            }
    )
    @PostMapping
    ResponseEntity<PautaResponse> create(@RequestBody PautaRequest pautaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.create(pautaRequest));
    }

    @Operation(summary = "Atualizar uma pauta existente",
            description = "Atualiza uma pauta existente com os dados fornecidos.",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Pauta atualizada com sucesso"),
                    @ApiResponse(responseCode = "422", description = "Erros na validação dos dados",
                            content = @Content(
                                    schema = @Schema(implementation = ErrosValidation.class))
                    ),
            }
    )
    @PutMapping("{id}")
    ResponseEntity<PautaResponse> update(@PathVariable Long id, @RequestBody PautaAlteradaRequest pautaRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pautaService.update(id, pautaRequest));
    }

    @Operation(summary = "Buscar uma pauta específica pelo seu ID",
            description = "Busca uma pauta existente com o ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pauta encontrada com sucesso"),
                    @ApiResponse(responseCode = "422", description = "Erros na validação dos dados",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrosValidation.class))
                    ),
            }
    )
    @GetMapping("{id}")
    ResponseEntity<PautaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findById(id));
    }

    @Operation(summary = "Listar todas as pautas",
            description = "Lista todas as pautas existentes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de pautas obtida com sucesso"),
            }
    )
    @GetMapping("")
    ResponseEntity<Page<PautaResponse>> findAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findAll(pageable));
    }

    @Operation(summary = "Excluir uma pauta pelo seu ID",
            description = "Exclui uma pauta existente com o ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pauta excluída com sucesso"),
            }
    )
    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        pautaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

