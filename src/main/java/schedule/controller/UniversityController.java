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
import schedule.dto.request.UniversityRequestDto;
import schedule.dto.response.UniversityResponseDto;
import schedule.model.University;
import schedule.service.UniversityService;
import schedule.service.mapper.UniversityMapper;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    private final UniversityService service;
    private final UniversityMapper mapper;

    public UniversityController(UniversityService service, UniversityMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public UniversityResponseDto add(@RequestBody @Valid UniversityRequestDto dto) {
        return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
    }

    @PostMapping("/add_all")
    public List<UniversityResponseDto> addAll(@RequestBody @Valid List<UniversityRequestDto> dtos) {
        return dtos.stream()
                .map(mapper::mapToModel)
                .map(service::add)
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UniversityResponseDto get(@PathVariable Long id) {
        return mapper.mapToDto(service.get(id));
    }

    @GetMapping("/get_all")
    public List<UniversityResponseDto> getAll() {
        return service.getAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UniversityResponseDto update(@PathVariable Long id,
                                        @RequestBody @Valid UniversityRequestDto dto) {
        University university = mapper.mapToModel(dto);
        university.setId(id);
        return mapper.mapToDto(university);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
