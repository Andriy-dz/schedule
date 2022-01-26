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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schedule.dto.request.GroupRequestDto;
import schedule.dto.response.GroupResponseDto;
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
        return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
    }

    @PostMapping("/add_all")
    public List<GroupResponseDto> addAll(@RequestBody @Valid List<GroupRequestDto> dtos) {
        return dtos.stream()
                .map(mapper::mapToModel)
                .map(service::add)
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GroupResponseDto get(@PathVariable Long id) {
        return mapper.mapToDto(service.get(id));
    }

    @GetMapping
    public List<GroupResponseDto> getAllByUnivId(@RequestParam Long universityId) {
        return service.getAllByUnivId(universityId)
                .stream().map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public GroupResponseDto update(@PathVariable Long id,
                                   @RequestBody @Valid GroupRequestDto dto) {
        Group group = mapper.mapToModel(dto);
        group.setId(id);
        return mapper.mapToDto(service.update(group));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
