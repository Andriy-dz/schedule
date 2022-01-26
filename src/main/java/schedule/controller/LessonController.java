package schedule.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schedule.dto.request.LessonRequestDto;
import schedule.dto.response.LessonResponseDto;
import schedule.exception.CustomException;
import schedule.model.Lesson;
import schedule.service.LessonService;
import schedule.service.mapper.LessonMapper;
import schedule.unit.DateTimePatternUtil;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private static final String DATE_PATTERN = DateTimePatternUtil.DATE_PATTERN;
    private final LessonService service;
    private final LessonMapper mapper;

    public LessonController(LessonService service, LessonMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public LessonResponseDto add(@RequestBody @Valid LessonRequestDto dto) {
        try {
            return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
        } catch (Exception e) {
            throw new CustomException("Can`t insert lesson - " + dto, e);
        }
    }

    @PostMapping("/add_all")
    public List<LessonResponseDto> addAll(@RequestBody @Valid List<LessonRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(mapper::mapToModel)
                    .map(service::add)
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t insert all lessons - " + dtos, e);
        }
    }

    @GetMapping("/{id}")
    public LessonResponseDto get(@PathVariable Long id) {
        try {
            return mapper.mapToDto(service.get(id));
        } catch (Exception e) {
            throw new CustomException("Can`t get lesson by id - " + id, e);
        }
    }

    @GetMapping("/get_all")
    public List<LessonResponseDto> getAllByStudentId(@RequestParam Long studentId,
                                                    @RequestParam
                                                    @DateTimeFormat(pattern = DATE_PATTERN)
                                                            LocalDate date) {
        try {
            return service.getAllByStudentId(studentId, date).stream()
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t get all lessons by student id - "
                    + studentId + " and data " + date, e);
        }
    }

    @PutMapping("/{id}")
    public LessonResponseDto update(@PathVariable Long id,
                                    @RequestBody @Valid LessonRequestDto dto) {
        try {
            Lesson lesson = mapper.mapToModel(dto);
            lesson.setId(id);
            return mapper.mapToDto(service.add(lesson));
        } catch (Exception e) {
            throw new CustomException("Can`t update lesson - " + dto + " by id - " + id, e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can`t delete lesson by id - " + id, e);
        }
    }
}
