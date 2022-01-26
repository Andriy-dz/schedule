package schedule.service;

import java.util.List;
import schedule.model.Teacher;

public interface TeacherService {
    Teacher add(Teacher teacher);

    Teacher get(Long id);

    List<Teacher> getAllByUnivId(Long id);

    Teacher update(Teacher teacher);

    void delete(Long id);
}
