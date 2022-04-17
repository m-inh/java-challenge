package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.ApiDemoApplication;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.UnauthorizedException;
import jp.co.axa.apidemo.services.EmployeeService;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiDemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class EmployeeControllerTest {

    private final String BASE_URL = "/api/v1/employees/";
    private static MockWebServer mockServer;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @BeforeClass
    public static void setUp() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void getAllEmployees_returnOk_whenDbHasData() throws Exception {
        // Arrange
        Employee[] employees = {
                new Employee("1", "employee 1", 100000, "accounting"),
                new Employee("2", "employee 2", 100000, "accounting"),
                new Employee("3", "employee 3", 100000, "accounting"),
                new Employee("4", "employee 4", 100000, "IT"),
        };

        Mockito.when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employees));

        // Act / Assert
        mockMvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("employees", hasSize(employees.length)))
                .andExpect(jsonPath("employees" + "[0].name", is(employees[0].getName())))
        ;
    }

    @Test
    public void getAllEmployees_return500InternalError_whenInternalErrorOccurs() throws Exception {
        // Arrange
        Mockito.when(employeeService.getAllEmployees()).thenThrow(new RuntimeException());

        // Act / Assert
        mockMvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
        ;
    }

    @Test
    public void getAllEmployees_return401Unauthoried_whenUnauthoriedUserAttemptToCall() throws Exception {
        // Arrange
        Mockito.when(employeeService.getAllEmployees()).thenThrow(new UnauthorizedException());

        // Act / Assert
        mockMvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
        ;
    }

}
