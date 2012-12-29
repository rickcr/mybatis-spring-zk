package net.learntechnology.empmaint.services;

import java.util.List;

import javax.annotation.Resource;

import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.mapper.DepartmentMapper;

import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	private DepartmentMapper departmentMapper;

	public List<Department> getAllDepartments() {
		return departmentMapper.getAllDepartments();
	}
}
