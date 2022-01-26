package schedule.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schedule.model.Audience;

@Repository
public interface AudienceDao extends JpaRepository<Audience, Long> {
    List<Audience> findAllByUniversityId(Long id);
}
