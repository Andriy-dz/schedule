package schedule.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Service;
import schedule.dao.LessonDao;
import schedule.model.Lesson;
import schedule.service.LessonService;
import schedule.unit.DateTimePatternUtil;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonDao lessonDao;
    private final DateTimeFormatter pattern =
            DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_TIME_PATTERN);

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public Lesson add(Lesson lesson) {
        return lessonDao.save(lesson);
    }

    @Override
    public Lesson get(Long id) {
        return lessonDao.getById(id);
    }

    @Override
    public Lesson update(Lesson lesson) {
        return lessonDao.save(lesson);
    }

    @Override
    public void delete(Long id) {
        lessonDao.deleteById(id);
    }

    @Override
    public List<Lesson> getAllByStudentId(Long id, LocalDate date) {
        LocalDateTime dateStart = date.atTime(0,0);
        LocalDateTime dateEnd = date.atTime(11,59);
        return lessonDao.findAllByStudentId(id, dateStart, dateEnd);
    }
}
