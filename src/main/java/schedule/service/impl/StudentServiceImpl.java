package schedule.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import schedule.dao.StudentDao;
import schedule.model.Student;
import schedule.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student add(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student get(Long id) {
        return studentDao.getById(id);
    }

    @Override
    public List<Student> getAllByGroupId(Long id) {
        return studentDao.findAllByGroupId(id);
    }

    @Override
    public Student update(Student student) {
        return studentDao.save(student);
    }

    @Override
    public void delete(Long id) {
        studentDao.deleteById(id);
    }
}
