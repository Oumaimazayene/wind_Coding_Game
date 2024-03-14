package com.demo.Api_Compilateur.Service;

import com.demo.Api_Compilateur.config.Entity.ClientRunRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@AllArgsConstructor
public class CompilateurServiceImpl implements  CompilateurService  {
    @Autowired
    private final RestTemplate restTemplate;



    public boolean compilerCode(ClientRunRequestBody requestBody) {
        String apiUrl = "http://localhost:8080/app/run"; // Mettez votre URL d'API appropriée ici

        // Appelez l'API de compilation
        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, requestBody, Map.class);

        // Renvoyez true ou false en fonction de la réponse de l'API de compilation
        return response.getStatusCode().is2xxSuccessful();
    }
}
