package schedule.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schedule.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student,Long> {
    @Query(value = "SELECT s FROM Group g JOIN g.students s WHERE g.id = ?1")
   List<Student> findAllByGroupId(Long id);
}
