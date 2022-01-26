package schedule.service;

import java.util.List;
import schedule.model.Group;

public interface GroupService {
    Group add(Group group);

    Group get(Long id);

    List<Group> getAllByUnivId(Long id);

    Group update(Group group);

    void delete(Long id);
}
