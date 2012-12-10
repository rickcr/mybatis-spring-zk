package net.learntechnology.empmaint.viewmodel;

import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.domain.Employee;
import net.learntechnology.empmaint.services.DepartmentService;
import net.learntechnology.empmaint.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Component("employeeVM")
@Scope("prototype")
public class EmployeeVM {
	private final static Logger logger = LoggerFactory.getLogger(EmployeeVM.class);

	List<Department> departments;
	List<Employee> employees;
	Employee selectedEmployee;
	Employee selectedEmployeeCopy;

	@Resource
	private EmployeeService employeeService;
	@Resource
	private DepartmentService departmentService;

	@Init
	public void init() {
		departments = departmentService.getAllDepartments();
		populateEmployees();
	}

	@NotifyChange("selectedEmployeeCopy")
	@Command
	public void editEmployee() {
		//I prefer to use a copy vs editing the selectedUser from our grid
		selectedEmployeeCopy = employeeService.getEmployee(selectedEmployee.getId());
		logger.debug("Employee for edit: {}", selectedEmployeeCopy);
		//update the Department in our Employee based on the correct one form our departments list
		//this enables it show up selected properly. (note: might be a better way than this? let me know)
		for(Department dept: departments) {
			if (selectedEmployeeCopy.getDepartment().getId() == dept.getId()) {
				selectedEmployeeCopy.setDepartment(dept);
				break;
			}
		}
	}

	@NotifyChange({"employees","selectedEmployeeCopy"})
	@Command
	public void submit() {
		if (selectedEmployeeCopy.getId() != null) {
			employeeService.updateEmployee(selectedEmployeeCopy);
		} else {
			employeeService.insertEmployee(selectedEmployeeCopy);
		}
		//clear our selectedEmployeeCopy
		selectedEmployeeCopy = null;
		//populate the employees again
		populateEmployees();
	}

	@NotifyChange({"employees","selectedEmployeeCopy"})
	@Command
	public void delete() {
		employeeService.deleteEmployee(selectedEmployeeCopy.getId());
		//clear our selectedEmployeeCopy
		selectedEmployeeCopy = null;
		//populate the employees again
		populateEmployees();
	}

	@NotifyChange("selectedEmployeeCopy")
	@Command
	public void newEmployee() {
		selectedEmployeeCopy = new Employee();
		//set first department selected
		selectedEmployeeCopy.setDepartment(departments.get(0));
	}

	private void populateEmployees() {
		this.employees = employeeService.getAllEmployees();
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public Employee getSelectedEmployeeCopy() {
		return selectedEmployeeCopy;
	}

	public void setSelectedEmployeeCopy(Employee selectedEmployeeCopy) {
		this.selectedEmployeeCopy = selectedEmployeeCopy;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
