package com.cg.ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.cg.ams.entity.Attendance;
import com.cg.ams.exception.DataNotFoundException;
import com.cg.ams.repository.IAttendanceRepository;

@Component
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	IAttendanceRepository attRepo;

	@Override
	public Attendance add(Attendance entity) {
		// TODO Auto-generated method stub
		Attendance att = attRepo.save(entity);
		att.setId(att.getId());
		att.setSubjectId(att.getSubjectId());
		att.setSubjectName(att.getSubjectName());
		att.setStudentId(att.getStudentId());
		att.setStudentName(att.getStudentName());
		att.setSemester(att.getSemester());
		att.setDate(att.getDate());
		att.setTotalClass(att.getTotalClass());
		att.setStatus(att.getStatus());
		att.setTotal(att.getTotal());
		att.setPercentage(att.getPercentage());
		att.setCourseId(att.getCourseId());
		att.setCourseName(att.getCourseName());

		return att;
	}

	@Override
	public void update(Attendance entity) {

		attRepo.save(entity);

	}

	@Override
	public void delete(Attendance entity) {
		attRepo.delete(entity);

	}

	@Override
	public Attendance findByName(String student_name) {
		return attRepo.findByAttName(student_name);

	}

	@Override
	public Attendance findByPk(long id) {
		Optional<Attendance> att = Optional.of(attRepo.getById(id));
		return attRepo.findById(id).orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));

	}

	@Override
	public List<Attendance> search(String name, int pageNo, int pageSize) {
		Pageable currentPage = PageRequest.of(pageNo, pageSize);

		return attRepo.findByStudentNameContainingAllIgnoreCase(name, currentPage);
	}

	@Override
	public long count() {
		return attRepo.count();
	}

}
