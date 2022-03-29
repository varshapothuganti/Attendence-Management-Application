package com.cg.ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.ams.entity.StudentEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.IStudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentRepository studentRepository;

	@Override
	public long add(StudentEntity entity) {

		Optional<StudentEntity> student = studentRepository.findById(entity.getId());
		if (student.isPresent()) {
			throw new DuplicateRecordException("Student Already exists with given id " + entity.getId());
		}
		StudentEntity std = studentRepository.save(entity);

		return std.getId();

	}

	@Override
	public void update(StudentEntity entity) {

		StudentEntity student = this.findByPk(entity.getId());
		if (student != null) {
			studentRepository.save(entity);
		}

	}

	@Override

	public void delete(StudentEntity entity) {

		StudentEntity student = this.findByPk(entity.getId());

		studentRepository.delete(student);
	}

	@Override
	public StudentEntity findByPk(long id) {

		return studentRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Student not found with id: " + id));
	}

	@Override
	public List<StudentEntity> search(String name) {

		Optional<List<StudentEntity>> sub1 = studentRepository
				.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name, name);
		if (sub1.isPresent()) {
			return sub1.get();
		}
		throw new RecordNotFoundException("Student not found with the given name " + name);
	}

	@Override
	public List<StudentEntity> search(String name, int pageNo, int pageSize) {
		Pageable currentPage = PageRequest.of(pageNo, pageSize);

		Optional<List<StudentEntity>> sub1 = studentRepository
				.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name, name, currentPage);
		if (sub1.isPresent()) {
			return sub1.get();
		}
		throw new RecordNotFoundException("Student not found with the given name " + name);

	}

	@Override

	public List<StudentEntity> findByName(String name) {
		Optional<List<StudentEntity>> sub1 = studentRepository.findByName(name);
		if (sub1.isPresent()) {
			return sub1.get();
		}
		throw new RecordNotFoundException("Student not found with the given name " + name);
	}

}
