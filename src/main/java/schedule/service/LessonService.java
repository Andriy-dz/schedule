package schedule.service;

import java.time.LocalDate;
import java.util.List;
import schedule.model.Lesson;

public interface LessonService {
    Lesson add(Lesson lesson);

    Lesson get(Long id);

    Lesson update(Lesson group);

    void delete(Long id);

    List<Lesson> getAllByStudentId(Long id, LocalDate date);
}
