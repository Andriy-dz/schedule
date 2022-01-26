package schedule.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schedule.model.Teacher;

@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long> {
    List<Teacher> findAllByUniversityId(Long id);
}
