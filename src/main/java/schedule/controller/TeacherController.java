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
import schedule.dto.request.TeacherRequestDto;
import schedule.dto.response.TeacherResponseDto;
import schedule.exception.CustomException;
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
        try {
            return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
        } catch (Exception e) {
            throw new CustomException("Can`t insert teacher - " + dto, e);
        }
    }

    @PostMapping("/add_all")
    public List<TeacherResponseDto> addAll(@RequestBody @Valid List<TeacherRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(mapper::mapToModel)
                    .map(service::add)
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t insert all teachers - " + dtos, e);
        }
    }

    @GetMapping("/{id}")
    public TeacherResponseDto get(@PathVariable Long id) {
        try {
            return mapper.mapToDto(service.get(id));
        } catch (Exception e) {
            throw new CustomException("Can`t get teacher by id - " + id, e);
        }
    }

    @GetMapping("/get_by_univ/{uneversityId}")
    public List<TeacherResponseDto> getAllByUnivId(@PathVariable Long uneversityId) {
        try {
            return service.getAllByUnivId(uneversityId).stream()
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t get all teachers by university id - "
                    + uneversityId, e);
        }
    }

    @PutMapping("/{id}")
    public TeacherResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid TeacherRequestDto dto) {
        try {
            Teacher teacher = mapper.mapToModel(dto);
            teacher.setId(id);
            return mapper.mapToDto(service.update(teacher));
        } catch (Exception e) {
            throw new CustomException("Can`t update teacher - " + dto + " by id - " + id, e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can`t delete teacher by id - " + id, e);
        }
    }
}
