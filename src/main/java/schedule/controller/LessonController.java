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
        return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
    }

    @PostMapping("/add_all")
    public List<LessonResponseDto> addAll(@RequestBody @Valid List<LessonRequestDto> dtos) {
        return dtos.stream()
                .map(mapper::mapToModel)
                .map(service::add)
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LessonResponseDto get(@PathVariable Long id) {
        return mapper.mapToDto(service.get(id));
    }

    @GetMapping
    public List<LessonResponseDto> getAllByStudentId(@RequestParam Long studentId,
                                                     @RequestParam
                                                     @DateTimeFormat(pattern = DATE_PATTERN)
                                                             LocalDate date) {
        return service.getAllByStudentId(studentId, date).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public LessonResponseDto update(@PathVariable Long id,
                                    @RequestBody @Valid LessonRequestDto dto) {
        Lesson lesson = mapper.mapToModel(dto);
        lesson.setId(id);
        return mapper.mapToDto(service.add(lesson));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
