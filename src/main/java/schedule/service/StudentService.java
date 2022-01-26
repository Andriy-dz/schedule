package schedule.service;

import java.util.List;
import schedule.model.Student;

public interface StudentService {
    Student add(Student student);

    Student get(Long id);

    List<Student> getAllByGroupId(Long id);

    Student update(Student student);

    void delete(Long id);
}
