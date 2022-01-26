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
import schedule.dto.request.StudentRequestDto;
import schedule.dto.response.StudentResponseDto;
import schedule.model.Student;
import schedule.service.StudentService;
import schedule.service.mapper.StudentMapper;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    private final StudentMapper mapper;

    public StudentController(StudentService service, StudentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public StudentResponseDto add(@RequestBody @Valid StudentRequestDto dto) {
        return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
    }

    @PostMapping("/add_all")
    public List<StudentResponseDto> addAll(@RequestBody @Valid List<StudentRequestDto> dtos) {
        return dtos.stream()
                .map(mapper::mapToModel)
                .map(service::add)
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentResponseDto get(@PathVariable Long id) {
        return mapper.mapToDto(service.get(id));
    }

    @GetMapping
    public List<StudentResponseDto> getByGroupId(@RequestParam Long groupId) {
        return service.getAllByGroupId(groupId).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public StudentResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid StudentRequestDto dto) {
        Student student = mapper.mapToModel(dto);
        student.setId(id);
        return mapper.mapToDto(service.update(student));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
