package schedule.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import schedule.dao.AudienceDao;
import schedule.model.Audience;
import schedule.service.AudienceService;

@Service
public class AudienceServiceImpl implements AudienceService {
    private final AudienceDao audienceDao;

    public AudienceServiceImpl(AudienceDao audienceDao) {
        this.audienceDao = audienceDao;
    }

    @Override
    public Audience add(Audience audience) {
        return audienceDao.save(audience);
    }

    @Override
    public Audience get(Long id) {
        return audienceDao.getById(id);
    }

    @Override
    public List<Audience> getAllByUnivId(Long id) {
        return audienceDao.findAllByUniversityId(id);
    }

    @Override
    public Audience update(Audience audience) {
        return audienceDao.save(audience);
    }

    @Override
    public void delete(Long id) {
        audienceDao.deleteById(id);
    }
}
