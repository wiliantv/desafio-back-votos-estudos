package com.wiliantv.votacao.exceptions.responses;

import lombok.Getter;

@Getter
public class Erros4XX {
    private String error400Code;
    private String message;

    public Erros4XX(Throwable ex){

        this.error400Code = ex.getClass().getName();
        this.message = ex.getMessage();
    }
}
