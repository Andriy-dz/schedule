package schedule.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.dto.request.GroupRequestDto;
import schedule.dto.response.GroupResponseDto;
import schedule.exception.CustomException;
import schedule.model.Group;
import schedule.service.GroupService;
import schedule.service.mapper.GroupMapper;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService service;
    private final GroupMapper mapper;

    public GroupController(GroupService service, GroupMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public GroupResponseDto add(@RequestBody @Valid GroupRequestDto dto) {
        try {
            return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
        } catch (Exception e) {
            throw new CustomException("Can`t insert group - " + dto, e);
        }
    }

    @PostMapping("/add_all")
    public List<GroupResponseDto> addAll(@RequestBody @Valid List<GroupRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(mapper::mapToModel)
                    .map(service::add)
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t insert all groups - " + dtos, e);
        }
    }

    @GetMapping("/{id}")
    public GroupResponseDto get(@PathVariable Long id) {
        try {
            return mapper.mapToDto(service.get(id));
        } catch (Exception e) {
            throw new CustomException("Can`t get group by id - " + id, e);
        }
    }

    @GetMapping("/get_all/{uneversityId}")
    public List<GroupResponseDto> getAllByUnivId(@PathVariable Long uneversityId) {
        try {
            return service.getAllByUnivId(uneversityId)
                    .stream().map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t get all groups by university id - " + uneversityId, e);
        }
    }

    @PutMapping("/{id}")
    public GroupResponseDto update(@PathVariable Long id,
                                   @RequestBody @Valid GroupRequestDto dto) {
        try {
            Group group = mapper.mapToModel(dto);
            group.setId(id);
            return mapper.mapToDto(service.update(group));
        } catch (Exception e) {
            throw new CustomException("Can`t update group - " + dto + " by id - " + id, e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can`t delete group by id - " + id, e);
        }
    }
}
