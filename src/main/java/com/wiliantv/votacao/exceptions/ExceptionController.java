package com.wiliantv.votacao.exceptions;

import com.wiliantv.votacao.exceptions.responses.Erros4XX;
import com.wiliantv.votacao.exceptions.responses.Erros5XX;
import com.wiliantv.votacao.exceptions.responses.ErrosValidation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrosValidation> constraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrosValidation(ex));
    }


    @ApiResponses(
            @ApiResponse(responseCode = "4XX", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Erros4XX.class)))
    )
    @ExceptionHandler(Generic4XXException.class)
    public ResponseEntity<Erros4XX> exception(Generic4XXException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Erros4XX(ex));
    }
    @ApiResponses(
            @ApiResponse(responseCode = "5XX", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Erros5XX.class)))
    )
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Erros5XX> exception(Throwable ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Erros5XX(ex));
    }
}
