package schedule.service;

import java.util.List;
import schedule.model.University;

public interface UniversityService {
    University add(University university);

    University get(Long id);

    List<University> getAll();

    University update(University university);

    void delete(Long id);
}
