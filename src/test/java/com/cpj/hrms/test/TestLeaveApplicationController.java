package com.cpj.hrms.test;

import com.cpj.hrms.controller.LeaveApplicationController;
import com.cpj.hrms.service.EmployeeService;
import com.cpj.hrms.service.LeaveApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LeaveApplicationController.class)
public class TestLeaveApplicationController {
    // get mock mvc
    @Autowired
    private MockMvc mockMvc;

    // get service layer
    @MockBean
    private LeaveApplicationService leaveApplicationService;

    @MockBean
    private EmployeeService employeeService;

    // test the application
    @Test
    public void contextLoads() throws Exception {

    }

    // test get all leave applications
    @Test
    public void testGetAllLeaveApplications() throws Exception {
        // use mock mvc to perform get request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/leave-applications").accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andReturn();
    }

    // test get leave application by employee id
    @Test
    public void testGetLeaveApplicationByEmployeeId() throws Exception {
        // use mock mvc to perform get request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/leave-applications/employee/1").accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andReturn();
    }
}
