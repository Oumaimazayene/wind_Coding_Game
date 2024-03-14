package com.demo.demo.Service;

import com.demo.demo.dtos.TestDTo;
import com.demo.demo.dtos.TypeDto;
import com.demo.demo.entity.Test;
import com.demo.demo.entity.Type;

import java.util.List;

public interface TestService {
    Test getTestById(Long id);
    List<TestDTo> getAllTests();
    TestDTo createTest(TestDTo testDTo);
    TestDTo updateTest(Long id, TestDTo testDTo);
    void deleteTest(Long id);
    void  deleteAllTests();
     void CreateTest_Logique(Long CompagneId );

}
