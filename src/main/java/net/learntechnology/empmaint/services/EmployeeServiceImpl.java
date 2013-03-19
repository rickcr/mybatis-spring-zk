package net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.domain.Employee;
import net.learntechnology.empmaint.mapper.EmployeeMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeMapper employeeMapper;

	public List<Employee> getAllEmployees() {
		return employeeMapper.getAllEmployees();
	}

	public void updateEmployee(Employee emp) {
		employeeMapper.updateEmployee(emp);
	}

	public void deleteEmployee(Integer id) {
		employeeMapper.deleteEmployee(id);
	}

	public Employee getEmployee(Integer id) {
		return employeeMapper.getEmployee(id);
	}

	public void insertEmployee(Employee emp) {
		employeeMapper.insertEmployee(emp);
	}
}
