package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.CompagnesRepository;
import com.demo.demo.Repository.TestRepository;
import com.demo.demo.Service.TestService;
import com.demo.demo.dtos.AnswerDTo;
import com.demo.demo.dtos.CompagnesDTo;
import com.demo.demo.dtos.Question_Tech_DTo;
import com.demo.demo.dtos.TestDTo;
import com.demo.demo.entity.*;
import com.demo.demo.mappers.TestMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final TestMapper testMapper;
    private final CompagnesRepository compagnesRepository;


    public TestServiceImpl(TestRepository testRepository, TestMapper testMapper, CompagnesRepository compagnesRepository) {
        this.testRepository = testRepository;
        this.testMapper = testMapper;
        this.compagnesRepository = compagnesRepository;
    }

    @Override
    public Test getTestById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    @Override
    public List<TestDTo> getAllTests() {
        return testRepository.findAll().stream()
                .map(testMapper::ToTestDto)
                .collect(Collectors.toList());
    }

    @Override
    public TestDTo createTest(TestDTo testDTo) {
        Long compagnesId = testDTo.getCompagnes_id();
        Compagnes compagnes = compagnesRepository.findById(compagnesId)
                .orElseThrow(() -> new EntityNotFoundException("Compagne not found with ID: " + compagnesId));

 Test test = testMapper.ToTest(testDTo);
 test.setCompagnes(compagnes);


        return testMapper.ToTestDto(testRepository.save(test));
    }


    @Override
    public TestDTo updateTest(Long id, TestDTo testDTo) {
        Optional<Test> existingTest = testRepository.findById(id);
        if (existingTest.isPresent()) {
            testMapper.updateTestFromDTO(testDTo, existingTest.get());
            return testMapper.ToTestDto(testRepository.save(existingTest.get()));
        }
        return null;
    }

    @Override
    public void deleteTest(Long id) {
        try {
            testRepository.deleteById(id);
            System.out.println("Test deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Test with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting Test");
            throw e;
        }
    }

    @Override
    public void deleteAllTests() {
        try {
            testRepository.deleteAll();
            System.out.println("All Tests deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all tests");
            throw e;
        }
    }





}


