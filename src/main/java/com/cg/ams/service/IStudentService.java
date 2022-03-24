

import java.util.List;

import com.cg.ams.entity.StudentEntity;

public interface IStudentService {
	public long add(StudentEntity entity);
	public void update(StudentEntity entity);
	public void delete(StudentEntity entity);
	public List<StudentEntity> findByName(String firstName);
	public StudentEntity findByPk(long id);
	public List<StudentEntity> search(StudentEntity entity, long pageNo, int pageSize);
	public List<StudentEntity> search(StudentEntity entity);


}
