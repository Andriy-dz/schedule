package schedule.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schedule.model.Group;

@Repository
public interface GroupDao extends JpaRepository<Group, Long> {
    List<Group> findAllByUniversityId(Long id);
}
