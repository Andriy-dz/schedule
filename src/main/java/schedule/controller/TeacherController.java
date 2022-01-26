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
import schedule.dto.request.TeacherRequestDto;
import schedule.dto.response.TeacherResponseDto;
import schedule.model.Teacher;
import schedule.service.TeacherService;
import schedule.service.mapper.TeacherMapper;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService service;
    private final TeacherMapper mapper;

    public TeacherController(TeacherService service, TeacherMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public TeacherResponseDto add(@RequestBody @Valid TeacherRequestDto dto) {
        return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
    }

    @PostMapping("/add_all")
    public List<TeacherResponseDto> addAll(@RequestBody @Valid List<TeacherRequestDto> dtos) {
        return dtos.stream()
                .map(mapper::mapToModel)
                .map(service::add)
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TeacherResponseDto get(@PathVariable Long id) {
        return mapper.mapToDto(service.get(id));
    }

    @GetMapping
    public List<TeacherResponseDto> getAllByUnivId(@RequestParam Long universityId) {
        return service.getAllByUnivId(universityId).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TeacherResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid TeacherRequestDto dto) {
        Teacher teacher = mapper.mapToModel(dto);
        teacher.setId(id);
        return mapper.mapToDto(service.update(teacher));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
