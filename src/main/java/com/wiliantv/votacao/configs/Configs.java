package com.wiliantv.votacao.configs;

import com.wiliantv.votacao.exceptions.responses.Erros4XX;
import com.wiliantv.votacao.exceptions.responses.Erros5XX;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configs {
    @SuppressWarnings("unused")
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }


    @Bean
    public OperationCustomizer responseStatusOperationCustomizer() {
        return (operation, handlerMethod) -> {
            operation.getResponses()
                    .addApiResponse(
                            "5XX",
                            new ApiResponse()
                                    .description("Erros internos do servidor")
                                    .content(
                                            new Content().addMediaType(
                                                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                                                    new MediaType().schema(new Schema<Erros5XX>().$ref("Erros5XX"))
                                            )
                                    ));
            operation.getResponses()
                    .addApiResponse(
                            "4XX",
                            new ApiResponse()
                                    .description("Erros internos do servidor")
                                    .content(
                                            new Content().addMediaType(
                                                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                                                    new MediaType().schema(new Schema<Erros4XX>().$ref("Erros4XX"))
                                            )
                                    ));
            return operation;
        };
    }
}
