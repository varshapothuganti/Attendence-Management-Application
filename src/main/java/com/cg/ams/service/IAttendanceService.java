package com.cg.ams.service;
import java.util.List;

import com.cg.ams.bean.Attendance;
import org.springframework.stereotype.Service;

@Service
public interface IAttendanceService {
	
	Attendance add(Attendance entity);
	void update(Attendance entity);
	void delete(Attendance entity);
	Attendance findByName(String student_name);
	Attendance findByPk(long id);
	List<Attendance> search(Attendance entity, long pageNo, int pageSize);
	List<Attendance> search(Attendance entity);

}
