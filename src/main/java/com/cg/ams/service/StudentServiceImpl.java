package com.cg.ams.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.entity.StudentEntity;
import com.cg.ams.repository.IStudentRepository;


@Service
public class StudentServiceImpl implements IStudentService{
	
	
	@Autowired
	IStudentRepository studentRepository;

	@Override
	public long add(StudentEntity entity) {
		
		Optional<StudentEntity> student= studentRepository.findById(entity.getId());
		if(student.isPresent())
		{
			throw new DuplicateRecordException("Student Already exists with given id "+ entity.getId());
		}
		StudentEntity stud = studentRepository.save(entity);

		return stud.getId();
	
	}

	@Override
	public void update(StudentEntity entity) {
		
		Optional<StudentEntity> student = studentRepository.findById(entity.getId());
        if (!student.isPresent()) {
            throw new RecordNotFoundException("Student not found with the id: "+entity.getId());
        }
        StudentEntity student1=student.get();
        student1.setSubject(entity.getSubject());
        student1.setRollNo(entity.getRollNo());
        student1.setFirstName(entity.getFirstName());
        student1.setLastName(entity.getLastName());
        student1.setDob(entity.getDob());
        student1.setGender(entity.getGender());
        student1.setMobileNo(entity.getMobileNo());
        student1.setEmailId(entity.getEmailId());
        student1.setFatherEmailId(entity.getFatherEmailId());
        student1.setFatherMobileNo(entity.getFatherMobileNo());
        student1.setProfilePic(entity.getProfilePic());
        studentRepository.save(student1);
		
	}

	@Override
	public void delete(StudentEntity entity) {
		
        Optional<StudentEntity> student = studentRepository.findById(entity.getId());
        if (!student.isPresent()) {
            throw new RecordNotFoundException("Student not found with the id: "+entity.getId());
        }
        studentRepository.delete(entity);
		
	}



	@Override
	public StudentEntity findByPk(long id) {

		return studentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Student not found with id: " + id));
	}

	

    
	@Override
	public List<StudentEntity> search(String name, int pageNo, int pageSize) {
        Pageable currentPage = PageRequest.of(pageNo, pageSize);
        
        return studentRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name,name, currentPage);

	}

	@Override
	public List<StudentEntity> findByName(String name){
      Optional<List<StudentEntity>> student = studentRepository.findByName(name);
      if (student.get().isEmpty()) {
          throw new RecordNotFoundException("Student not found with the given name "+ name);
      }
    return student.get();


	}

    @Override
    public List<StudentEntity> search(String name) {
    	
    	 return studentRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name,name);
    }
	


}
