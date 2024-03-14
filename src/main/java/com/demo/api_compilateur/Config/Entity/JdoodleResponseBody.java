package com.demo.Api_Compilateur.config.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JdoodleResponseBody {
    private String output;
    private Integer statusCode;
    private String memory;
    private String cpuTime;
}
