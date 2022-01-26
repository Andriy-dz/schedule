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
import schedule.exception.CustomException;
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
        try {
            return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
        } catch (Exception e) {
            throw new CustomException("Can`t insert university - " + dto, e);
        }
    }

    @PostMapping("/add_all")
    public List<UniversityResponseDto> addAll(@RequestBody @Valid List<UniversityRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(mapper::mapToModel)
                    .map(service::add)
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t insert all universities - " + dtos, e);
        }
    }

    @GetMapping("/{id}")
    public UniversityResponseDto get(@PathVariable Long id) {
        try {
            return mapper.mapToDto(service.get(id));
        } catch (Exception e) {
            throw new CustomException("Can`t get university by id - " + id, e);
        }
    }

    @GetMapping("/get_all")
    public List<UniversityResponseDto> getAll() {
        try {
            return service.getAll().stream()
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t get all universities", e);
        }
    }

    @PutMapping("/{id}")
    public UniversityResponseDto update(@PathVariable Long id,
                                        @RequestBody @Valid UniversityRequestDto dto) {
        try {
            University university = mapper.mapToModel(dto);
            university.setId(id);
            return mapper.mapToDto(university);
        } catch (Exception e) {
            throw new CustomException("Can`t update university - " + dto + " by id - " + id, e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can`t delete university by id - " + id, e);
        }
    }
}
