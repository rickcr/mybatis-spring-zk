package net.learntechnology.empmaint.net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.BaseIntegrationTest;
import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.services.DepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class DepartmentIntegrationTest extends BaseIntegrationTest {
	private final static Logger logger = LoggerFactory.getLogger(DepartmentIntegrationTest.class);

	@Resource
	private DepartmentService departmentService;

	@Test
	public void getDepartmentsTest() {
		List<Department> departments = departmentService.getAllDepartments();
		for(Department d: departments) {
			logger.debug("Dept: {}", d);
		}
		Assert.assertTrue(departments.size() > 1);
	}
}
