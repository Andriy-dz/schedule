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
import schedule.dto.request.StudentRequestDto;
import schedule.dto.response.StudentResponseDto;
import schedule.exception.CustomException;
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
        try {
            return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
        } catch (Exception e) {
            throw new CustomException("Can`t insert student - " + dto, e);
        }
    }

    @PostMapping("/add_all")
    public List<StudentResponseDto> addAll(@RequestBody @Valid List<StudentRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(mapper::mapToModel)
                    .map(service::add)
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t insert all students - " + dtos, e);
        }
    }

    @GetMapping("/{id}")
    public StudentResponseDto get(@PathVariable Long id) {
        try {
            return mapper.mapToDto(service.get(id));
        } catch (Exception e) {
            throw new CustomException("Can`t get student by id - " + id, e);
        }
    }

    @GetMapping("/get_all/{groupId}")
    public List<StudentResponseDto> getByGroupId(@PathVariable Long groupId) {
        try {
            return service.getAllByGroupId(groupId).stream()
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t get all students by group id - " + groupId, e);
        }
    }

    @PutMapping("/{id}")
    public StudentResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid StudentRequestDto dto) {
        try {
            Student student = mapper.mapToModel(dto);
            student.setId(id);
            return mapper.mapToDto(service.update(student));
        } catch (Exception e) {
            throw new CustomException("Can`t update student - " + dto + " by id - " + id, e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can`t delete student by id - " + id, e);
        }
    }
}
