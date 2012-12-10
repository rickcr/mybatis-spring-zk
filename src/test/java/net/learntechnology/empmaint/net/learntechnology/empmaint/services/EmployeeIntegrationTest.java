package net.learntechnology.empmaint.net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.BaseIntegrationTest;
import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.domain.Employee;
import net.learntechnology.empmaint.services.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class EmployeeIntegrationTest extends BaseIntegrationTest {
	private final static Logger logger = LoggerFactory.getLogger(EmployeeIntegrationTest.class);

	@Resource
	private EmployeeService employeeService;

	@Test
	public void getAllEmployeesTest() {
		List<Employee> employees = employeeService.getAllEmployees();
		for(Employee emp: employees) {
			logger.debug("EMP: {}", emp);
		}
		Assert.assertTrue(employees.size() > 1);
	}

	@Test
	public void getEmployeeTest() {
		Employee emp = employeeService.getEmployee(1);
		logger.debug("Emp returned {}", emp);
		Assert.assertEquals("John", emp.getFirstName());
	}

	@Test
	@Transactional
	public void insertEmployeeTest() {
		Department d = new Department();
		d.setId(100);
		Employee emp = new Employee(null, "TestFirstName", "TestLastName", 43, d);
		employeeService.insertEmployee(emp);
		emp = employeeService.getEmployee(emp.getId());
		logger.debug("Emp returned {}", emp);
		Assert.assertEquals("TestFirstName", emp.getFirstName());
	}
}
