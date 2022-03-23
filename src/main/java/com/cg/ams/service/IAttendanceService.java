package com.cg.ams.service
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.bean.Attendance;
@Service
public interface IAttendanceService {
	
	public Attendance add(Attendance entity);
	public void update(Attendance entity);
	public void delete(Attendance entity);
	public Attendance findByName(String student_name);
	public Attendance findByPk(long id);
	public List<Attendance> search(Attendance entity, long pageNo, int pageSize);
	public List<Attendance> search(Attendance entity);

}
