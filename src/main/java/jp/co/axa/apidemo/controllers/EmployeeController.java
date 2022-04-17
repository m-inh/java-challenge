package jp.co.axa.apidemo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jp.co.axa.apidemo.dto.*;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static jp.co.axa.apidemo.constant.ApiResponse.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RESPONSE_CODE_200,
                    description = RESPONSE_DESCRIPTION_200,
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Employee.class) //todo: dto implementation
                    )}),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_401,
                    description = RESPONSE_DESCRIPTION_401,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_500,
                    description = RESPONSE_DESCRIPTION_500,
                    content = @Content()
            ),
    })
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public GetAllEmployeeResponse getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new GetAllEmployeeResponse(employees);
    }

    @Operation(summary = "Get employee by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RESPONSE_CODE_200,
                    description = RESPONSE_DESCRIPTION_200,
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Employee.class) // todo: dto
                    )}),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_401,
                    description = RESPONSE_DESCRIPTION_401,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_404,
                    description = RESPONSE_DESCRIPTION_404,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_500,
                    description = RESPONSE_DESCRIPTION_500,
                    content = @Content()
            ),
    })
    @GetMapping("/{employeeId}")
    @ResponseStatus(code = HttpStatus.OK)
    public GetEmployeeResponse getEmployeeById(@PathVariable(name = "employeeId") String employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new GetEmployeeResponse(employeeId, employee.getName(), employee.getSalary(), employee.getDepartment());
    }

    @Operation(summary = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RESPONSE_CODE_201,
                    description = RESPONSE_DESCRIPTION_201,
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Employee.class) // todo: dto
                    )}),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_400,
                    description = RESPONSE_DESCRIPTION_400,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_401,
                    description = RESPONSE_DESCRIPTION_401,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_500,
                    description = RESPONSE_DESCRIPTION_500,
                    content = @Content()
            ),
    })
    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setSalary(request.getSalary());
        employee.setDepartment(request.getDepartment());

        employeeService.createEmployee(employee);

        log.info("New employee has been registered: {}", employee.getId());

        return new CreateEmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getSalary(),
                employee.getDepartment()
        );
    }

    @Operation(summary = "Delete an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RESPONSE_CODE_204,
                    description = RESPONSE_DESCRIPTION_204,
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Employee.class) // todo: dto
                    )}),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_401,
                    description = RESPONSE_DESCRIPTION_401,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_404,
                    description = RESPONSE_DESCRIPTION_404,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_500,
                    description = RESPONSE_DESCRIPTION_500,
                    content = @Content()
            ),
    })
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable(name = "employeeId") String employeeId) {
        employeeService.deleteEmployee(employeeId);
        log.info("An employee has been removed: {}", employeeId);
    }

    @Operation(summary = "Update information of an employee")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RESPONSE_CODE_204,
                    description = RESPONSE_DESCRIPTION_204,
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Employee.class) // todo: dto
                    )}),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_400,
                    description = RESPONSE_DESCRIPTION_400,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_401,
                    description = RESPONSE_DESCRIPTION_401,
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = RESPONSE_CODE_500,
                    description = RESPONSE_DESCRIPTION_500,
                    content = @Content()
            ),
    })
    @PutMapping("/{employeeId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateEmployee(@RequestBody UpdateEmployeeRequest request,
                               @PathVariable(name = "employeeId") String employeeId) {
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName(request.getName());
        employee.setSalary(request.getSalary());
        employee.setDepartment(request.getDepartment());

        employeeService.updateEmployee(employeeId, employee);

        log.info("An employee has been updated: {}", employeeId);
    }

}
