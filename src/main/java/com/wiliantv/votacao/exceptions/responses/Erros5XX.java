package com.wiliantv.votacao.exceptions.responses;

import lombok.Getter;

@Getter
public class Erros5XX {
    private String error500Code;
    private String message;

    public Erros5XX(Throwable ex){
        error500Code  = ex.getClass().getName();
        this.message = ex.getMessage();
    }
}
