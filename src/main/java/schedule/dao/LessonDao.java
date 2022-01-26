package schedule.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schedule.model.Lesson;

@Repository
public interface LessonDao extends JpaRepository<Lesson, Long> {
    @Query(value = "SELECT l FROM Lesson l JOIN l.group g "
            + "JOIN g.students s where s.id = ?1 AND l.date BETWEEN ?2 AND ?3")
    List<Lesson> findAllByStudentId(Long id, LocalDateTime dateStart, LocalDateTime dateEnd);
}
