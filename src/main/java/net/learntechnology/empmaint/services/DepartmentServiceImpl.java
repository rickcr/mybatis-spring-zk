package net.learntechnology.empmaint.services;

import java.util.List;

import javax.annotation.Resource;

import net.learntechnology.empmaint.domain.Department;
import net.learntechnology.empmaint.mapper.DepartmentMapper;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("departmentService")
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	private DepartmentMapper departmentMapper;

	public List<Department> getAllDepartments() {
		return departmentMapper.getAllDepartments();
	}
}
