package com.cg.ams.service;

import java.util.ArrayList;

import java.util.List;




import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.dto.StudentInputDTO;
import com.cg.ams.dto.StudentOutputDTO;
import com.cg.ams.entity.StudentEntity;
import com.cg.ams.repository.IStudentRepository;


/**
 * Implements IStudentService interface
 *
 * @author Varsha
 */
@Service
public class StudentServiceImpl implements IStudentService{
	
	
	@Autowired
	IStudentRepository studentRepository;
	
	private String message = "Cannot find any Student record with id: ";

	/**
	 * Add student into database
	 *
	 * @param stdDTO
	 * @return id
	 */
	@Override
	public long add(StudentInputDTO stdDTO) {
		
		StudentEntity entity = new StudentEntity(stdDTO);
		Optional<StudentEntity> student = studentRepository.findById(entity.getId());
		if (student.isPresent()) {
			throw new DuplicateRecordException("The faculty with id: " + entity.getId() + " already exists!");
		}
		StudentEntity std=studentRepository.save(entity);
		return std.getId();
	}
	
	/**
	 * updates a row in the database. Throws RecordFoundException if the row
	 * doesn't exist.
	 *
	 * @param StdDTO
	 */
	@Override
	public void update(StudentInputDTO stdDTO) {
		
		StudentEntity entity = new StudentEntity(stdDTO);
		Optional<StudentEntity> student = studentRepository.findById(entity.getId());
		if (!student.isPresent()) {
			throw new RecordNotFoundException(message + entity.getId());
		}
		studentRepository.save(entity);
		
	}
	/**
	 * Deletes a row from the database. Throws RecordNotFoundException if the row
	 * doesn't exist.
	 *
	 * @param stdDTO
	 */
	@Override
	public void delete(StudentInputDTO stdDTO) {
		StudentEntity entity = new StudentEntity(stdDTO);
		StudentEntity student = studentRepository.findById(entity.getId()).orElseThrow(
				() -> new RecordNotFoundException(message + entity.getId()));

		studentRepository.deleteById(student.getId());
		
	}
	/**
	 * Searches the database for a record based on the name. Throws
	 * RecordNotFoundException if not found.
	 *
	 * @param name
	 * @return StudentOutputDTO
	 */
	@Override
	public List<StudentOutputDTO> findByName(String name) {
		Optional<List<StudentEntity>> student = studentRepository.findByName(name);
		List<StudentOutputDTO> std = new ArrayList<>();
		for(StudentEntity std1 : student.get()) {
			std.add(new StudentOutputDTO(std1));
		}
		return std;
	}
	/**
	 * Searches the database for a record based on the Primary Key (id). Throws
	 * RecordNotFoundException if not found.
	 *
	 * @param id
	 * @return StudentOutputDto
	 */

	@Override
	public StudentOutputDTO findByPk(long id) {
		Optional<StudentEntity> student = studentRepository.findById(id);
		if (!student.isPresent()) {
			throw new RecordNotFoundException(message + id);
		}
		return new StudentOutputDTO(student.get());
	}

	/**
	 * Searches the database based on part of a name. Returns result after
	 * paginating.
	 *
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return List<StudentOutputDTO>
	 */
	@Override
	public List<StudentOutputDTO> search(String name, int pageNo, int pageSize) {
		Pageable currentPage = PageRequest.of(pageNo, pageSize);
		List<StudentEntity> student =studentRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name,name, currentPage);
		List<StudentOutputDTO> std = new ArrayList<>();
		for(StudentEntity std1 : student) {
			std.add(new StudentOutputDTO(std1));
		}
		return std;
	}
	
	/**
	 * Searches the database based on part of a name
	 *
	 * @param name
	 * @return List<studentOutputDTO>
	 */
	@Override
	public List<StudentOutputDTO> search(String name) {
		List<StudentEntity> student = studentRepository.findStudentByFirstNameOrLastName(name,name);
		List<StudentOutputDTO> std = new ArrayList<>();
		for(StudentEntity std1 : student) {
			std.add(new StudentOutputDTO(std1));
		}
		return std;
	}

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
	}



