package com.demo.demo.Controller;

import com.demo.demo.Service.TestService;
import com.demo.demo.dtos.Question_Tech_DTo;
import com.demo.demo.dtos.TestDTo;
import com.demo.demo.entity.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {
    private  final TestService testService ;

    public TestController(TestService testService) {
        this.testService = testService;
    }
    @GetMapping("/{id}")
    public Test getTestById(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @GetMapping("/tests")
    public List<TestDTo> getAllTests() {
        return testService.getAllTests();
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<?> createTest(@RequestBody TestDTo testDTo) {
        try {
            TestDTo  createTest= testService.createTest(testDTo);
            return new ResponseEntity<>(createTest, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to create test: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public TestDTo updateType(@PathVariable Long id, @RequestBody TestDTo testDTo) {
        return testService.updateTest(id, testDTo);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }

    @DeleteMapping("/test")
    public void deleteAllTests() {
      testService.deleteAllTests();
    }



    @PostMapping("/create/test_logique")
    public ResponseEntity<String> createTestLogique(@RequestParam Long compagnesId) {
        try {
            testService.CreateTest_Logique(compagnesId);
            return ResponseEntity.ok("Test créé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la création du test: " + e.getMessage());
        }
    }
}
