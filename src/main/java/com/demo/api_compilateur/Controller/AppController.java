package com.demo.Api_Compilateur.Controller;


import com.demo.Api_Compilateur.config.Entity.ClientRunRequestBody;
import com.demo.Api_Compilateur.utils.LanguagesManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import  com.demo.Api_Compilateur.utils.RequestHandler ;

@RestController
@AllArgsConstructor
@RequestMapping("/app")
public class AppController {

    private final LanguagesManager languagesManager;
    private final RequestHandler requestHandler;
    @GetMapping("/langs")
    public ResponseEntity<Map<String, Object>> getLangs() {
        System.out.println("GET: '/langs'");
        Map<String, Object> response = new HashMap<>();
        response.put("langs", languagesManager.getLanguagesMap());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/run")
    public ResponseEntity<Map<String, Object>> postRunRequest(@RequestBody ClientRunRequestBody body) {
        System.out.println("POST: '/run'");

        if (!validatePostRun(body)) {
            System.out.println("Invalid body parameters");
            return ResponseEntity.badRequest().build();
        }

        try {
            String index = languagesManager.getLanguageVersionIndex(body.getLang(), body.getVersion());
            ResponseEntity<String> result = requestHandler.postRunRequest(body.getLang(), index, body.getProgram(), body.getStdin());

            System.out.println("postRunRequest on jdoodle-success: " + result);
            Map<String, Object> response = new HashMap<>();
            response.put("runResult", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Request fail");
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validatePostRun(ClientRunRequestBody reqBody) {
        return reqBody != null &&
                reqBody.getLang() != null &&
                reqBody.getVersion() != null &&
                reqBody.getProgram() != null &&
                !reqBody.getProgram().isEmpty() &&
                languagesManager.isLangSupported(reqBody.getLang(), reqBody.getVersion());
    }
}
