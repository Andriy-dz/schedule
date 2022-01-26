package schedule.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import schedule.dao.UniversityDao;
import schedule.model.University;
import schedule.service.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {
    private final UniversityDao universityDao;

    public UniversityServiceImpl(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    @Override
    public University add(University university) {
        return universityDao.save(university);
    }

    @Override
    public University get(Long id) {
        return universityDao.getById(id);
    }

    @Override
    public List<University> getAll() {
        return universityDao.findAll();
    }

    @Override
    public University update(University university) {
        return universityDao.save(university);
    }

    @Override
    public void delete(Long id) {
        universityDao.deleteById(id);
    }
}
