package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.NotFoundException;

import java.util.List;

public interface EmployeeService {

    /**
     * Get all employees of company.
     *
     * @return an employee list
     */
    List<Employee> getAllEmployees();

    /**
     * Get an employee information by his/her ID.
     *
     * @param employeeId the employee ID
     * @return an employee object containing ID, Name, Salary, Department
     * @throws NotFoundException if requested employee ID is not existed
     */
    Employee getEmployeeById(String employeeId);

    /**
     * Create a new Employee for company.
     *
     * @param employee the employee object containing Name, Salary, Department
     */
    void createEmployee(Employee employee);

    /**
     * Delete an employee from company.
     *
     * @param employeeId the employee ID
     * @throws NotFoundException if requested employee ID is not existed
     */
    void deleteEmployee(String employeeId);

    /**
     * Update an employee information by his/her ID.
     *
     * @param employeeId the employee ID
     * @param updatedEmployee the employee information that will be updated to
     * @throws NotFoundException if requested employee ID is not existed
     */
    void updateEmployee(String employeeId, Employee updatedEmployee);
}