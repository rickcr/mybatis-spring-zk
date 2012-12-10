package net.learntechnology.empmaint.services;

import net.learntechnology.empmaint.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	private DepartmentMapper departmentMapper;

	public List getAllDepartments() {
		return departmentMapper.getAllDepartments();
	}
}
