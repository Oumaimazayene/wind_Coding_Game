package com.demo.Api_Compilateur.Service;


import com.demo.Api_Compilateur.config.Entity.ClientRunRequestBody;

public interface CompilateurService {
    boolean compilerCode(ClientRunRequestBody requestBody);

}
