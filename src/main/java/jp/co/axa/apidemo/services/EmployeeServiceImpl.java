package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.NotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Get all employees of company.
     *
     * @return an employee list
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Get an employee information by his/her ID.
     *
     * @param employeeId the employee ID
     * @return an employee object containing ID, Name, Salary, Department
     * @throws NotFoundException if requested employee ID is not existed
     */
    public Employee getEmployeeById(String employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (!employee.isPresent()) {
            throw new NotFoundException();
        }

        return employee.get();
    }

    /**
     * Create a new Employee for company.
     *
     * @param employee the employee object containing Name, Salary, Department
     */
    public void createEmployee(Employee employee) {
        // Generate a UUID for new employee
        // The id has a format like this: 123e4567-e89b-42d3-a456-556642440000
        UUID uuid = UUID.randomUUID();

        employee.setId(uuid.toString());

        employeeRepository.save(employee);
    }

    /**
     * Delete an employee from company.
     *
     * @param employeeId the employee ID
     * @throws NotFoundException if requested employee ID is not existed
     */
    public void deleteEmployee(String employeeId) {
        // Check if requested employee exists
        Employee employee = getEmployeeById(employeeId);

        employeeRepository.deleteById(employeeId);
    }

    /**
     * Update an employee information by his/her ID.
     *
     * @param employeeId the employee ID
     * @param updatedEmployee the employee information that will be updated to
     * @throws NotFoundException if requested employee ID is not existed
     */
    public void updateEmployee(String employeeId, Employee updatedEmployee) {
        // Check if requested employee exists
        Employee employee = getEmployeeById(employeeId);

        employeeRepository.save(updatedEmployee);
    }
}