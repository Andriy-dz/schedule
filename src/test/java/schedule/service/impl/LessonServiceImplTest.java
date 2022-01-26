package schedule.service.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import schedule.dao.LessonDao;
import schedule.model.Audience;
import schedule.model.Group;
import schedule.model.Lesson;
import schedule.model.Teacher;

@ExtendWith(MockitoExtension.class)
class LessonServiceImplTest {
    @InjectMocks
    private LessonServiceImpl lessonService;

    @Mock
    private LessonDao lessonDao;

    @Test
    void getAllByStudentId_ok() {
        Lesson lesson = new Lesson();
        lesson.setTeacher(new Teacher());
        lesson.setAudience(new Audience());
        lesson.setGroup(new Group());
        lesson.setDate(LocalDateTime.of(2022,1, 1, 9, 0));
        lesson.setId(1L);
        Mockito.when(lessonDao.findAllByStudentId(1L,
                LocalDateTime.of(2022,1, 1, 0, 0),
                LocalDateTime.of(2022,1, 1, 11, 59)))
                .thenReturn(List.of(lesson));
        List<Lesson> actual = lessonService.getAllByStudentId(1L,
                LocalDate.of(2022,1, 1));
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(LocalDateTime.of(2022,1, 1, 9, 0), actual.get(0).getDate());
    }
}