package com.demo.Api_Compilateur.config.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientRunRequestBody {

    private String lang;
    private String version;
    private String program;
    private String stdin;
}