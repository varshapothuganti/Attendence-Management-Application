package com.cg.ams.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ams.entity.Attendance;

@Service
public interface IAttendanceService {

	public Attendance add(Attendance entity);

	public void update(Attendance entity);

	public void delete(Attendance entity);

	public Attendance findByName(String studentName);

	public Attendance findByPk(long id);

	public List<Attendance> search(String name, int pageNo, int pageSize);

	public long count();

}
