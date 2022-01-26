package schedule.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import schedule.dao.TeacherDao;
import schedule.model.Teacher;
import schedule.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public Teacher add(Teacher teacher) {
        return teacherDao.save(teacher);
    }

    @Override
    public Teacher get(Long id) {
        return teacherDao.getById(id);
    }

    @Override
    public List<Teacher> getAllByUnivId(Long id) {
        return teacherDao.findAllByUniversityId(id);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return teacherDao.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherDao.deleteById(id);
    }
}
