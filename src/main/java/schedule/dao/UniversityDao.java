package schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schedule.model.University;

@Repository
public interface UniversityDao extends JpaRepository<University, Long> {
}
