package com.cg.ams.service;

import com.cg.ams.entity.Attendance;
import com.cg.ams.repository.IAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceServiceImpl implements IAttendanceService {

    @Autowired
    IAttendanceRepository attRepo;

    @Override
    public Attendance add(Attendance entity) {
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
        // TODO Auto-generated method stub
        attRepo.delete(entity);

    }

    @Override
    public Attendance findByName(String student_name) {
        return attRepo.findByAttName(student_name);

    }

    @Override
    public Attendance findByPk(long id) {
        Attendance att = attRepo.getById(id);
        return att;
    }

    @Override
    public List<Attendance> search(Attendance entity, long pageNo, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Attendance> search(Attendance entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
