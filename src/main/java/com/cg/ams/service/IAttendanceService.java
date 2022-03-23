package com.cg.ams.service;

import com.cg.ams.entity.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

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
