package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.ApiDemoApplication;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiDemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class EmployeeControllerTest {

    private final String BASE_URL = "/api/v1/employees";
    public static MockWebServer mockWebServer;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @BeforeClass
    public static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        mockWebServer.shutdown();
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
        mockMvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON)
//                        .header(HttpHeaders.AUTHORIZATION, VALID_TOKEN)
                )
//                .andExpect(status().isOk())
//                .andExpect(is())
//                .andExpect(jsonPath(ERROR_CODE, is( "00")))
//                .andExpect(jsonPath(ERROR_MESSAGE, containsString("Success")))
//                .andExpect(jsonPath(RESULT, hasSize(1)))
//                .andExpect(jsonPath(RESULT + "[0].id", is(Math.toIntExact(1))))
                .andExpect(jsonPath("employees" + "[0].name", is(employees[0].getName())))
//                .andExpect(jsonPath(RESULT_NETWORKS + "[1].id", is(Math.toIntExact(LIST.get(1).getId()))))
//                .andExpect(jsonPath(RESULT_NETWORKS + "[1].name", is(LIST.get(1).getName())))
//                .andExpect(jsonPath(RESULT_NETWORKS + "[2].id", is(Math.toIntExact(LIST.get(2).getId()))))
//                .andExpect(jsonPath(RESULT_NETWORKS + "[2].name", is(LIST.get(2).getName())))
//                .andExpect(jsonPath(RESULT_NETWORKS + "[3].id", is(Math.toIntExact(LIST.get(3).getId()))))
//                .andExpect(jsonPath(RESULT_NETWORKS + "[3].name", is(LIST.get(3).getName())))
//                .andExpect(jsonPath(RESULT_NETWORKS + "[4].id", is(Math.toIntExact(LIST.get(4).getId()))))
//                .andExpect(jsonPath(RESULT_NETWORKS + "[4].name", is(LIST.get(4).getName())));
        ;
    }


//    @Test
//    public void testGetListNetworks00_service_unavailable() throws Exception {
//        mockMvc.perform(get(BASE_URL)
//                                .contentType(MediaType.APPLICATION_JSON)
////                        .header(HttpHeaders.AUTHORIZATION, VALID_TOKEN)
//                )
//                .andExpect(status().isServiceUnavailable())
//        ;
////                .andExpect(jsonPath(ERROR_CODE, is("503")))
////                .andExpect(jsonPath(ERROR_MESSAGE, containsString("Loyalty")));
//    }


//    @Test
//    public void testGetListNetwork_token_missing() throws Exception {
//        mockMvc.perform(get(BASE_URL)
//                                .contentType(MediaType.APPLICATION_JSON)
////                        .header(HttpHeaders.AUTHORIZATION, VALID_TOKEN)
//                )
//                .andExpect(status().isUnauthorized());
////                .andExpect(jsonPath(ERROR_CODE, is("401")))
////                .andExpect(jsonPath(ERROR_MESSAGE, is("Unauthorized")));
//
////        mockMvc.perform(post(this.getUrl())
////                        .content(JsonMapper.writeValueAsString(grantPointRequest))
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andDo(print())
////                .andExpect(status().isBadRequest())
////                .andExpect(jsonPath("$.code").value(TKErrorCode.CHECKSUM_ERROR_CODE));
//    }

}
