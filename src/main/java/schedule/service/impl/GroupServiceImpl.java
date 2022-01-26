package schedule.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import schedule.dao.GroupDao;
import schedule.model.Group;
import schedule.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group add(Group group) {
        return groupDao.save(group);
    }

    @Override
    public Group get(Long id) {
        return groupDao.getById(id);
    }

    @Override
    public List<Group> getAllByUnivId(Long id) {
        return groupDao.findAllByUniversityId(id);
    }

    @Override
    public Group update(Group group) {
        return groupDao.save(group);
    }

    @Override
    public void delete(Long id) {
        groupDao.deleteById(id);
    }
}
