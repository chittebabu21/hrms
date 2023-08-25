package com.cpj.hrms.test;

import com.cpj.hrms.controller.EmployeeController;
import com.cpj.hrms.service.ClaimService;
import com.cpj.hrms.service.EmployeeService;
import com.cpj.hrms.service.LeaveApplicationService;
import com.cpj.hrms.service.MCSubmissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class TestEmployeeController {
    // get mock mvc
    @Autowired
    private MockMvc mockMvc;

    // get service layer
    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private ClaimService claimService;

    @MockBean
    private LeaveApplicationService leaveApplicationService;

    @MockBean
    private MCSubmissionService mcSubmissionService;

    // test the application
    @Test
    public void contextLoads() throws Exception {

    }

    // test get all employees
    @Test
    public void testGetAllEmployees() throws Exception {
        // use mock mvc to perform get request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andReturn();

        // assert response as 200
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        // assert content
        String content = mvcResult.getResponse().getContentAsString();
    }

    // test get employees by position
    @Test
    public void testGetEmployeeByEmployeePosition() throws Exception {
        // use mock mvc to perform get request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employees/position/manager").accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andReturn();
    }

    // test post new employee
    @Test
    public void testPostNewEmployee() throws Exception {
        // use mock mvc to perform post request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"employeeId\": 1, \"employeeName\": \"John Doe\", \"employeePosition\": \"manager\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andReturn();
    }
}
