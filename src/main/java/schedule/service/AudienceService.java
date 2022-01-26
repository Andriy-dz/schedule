package schedule.service;

import java.util.List;
import schedule.model.Audience;

public interface AudienceService {
    Audience add(Audience audience);

    Audience get(Long id);

    List<Audience> getAllByUnivId(Long id);

    Audience update(Audience audience);

    void delete(Long id);
}
