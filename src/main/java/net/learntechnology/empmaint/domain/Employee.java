package net.learntechnology.empmaint.domain;


public class Employee extends BaseVO {
	private static final long serialVersionUID = -8658719776854779187L;
	private Integer id;
	private Integer age;
	private String firstName;
	private String lastName;
	private Department department;

	public Employee() {
	}

	public Employee(Integer id, String firstName, String lastName, Integer age, Department department) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
