package net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.domain.Employee;

import java.util.List;

public interface EmployeeService {
	List<Employee> getAllEmployees();
    void updateEmployee(Employee emp);
    void deleteEmployee(Integer id);
    public Employee getEmployee(Integer id);
    public void insertEmployee(Employee emp);

}
