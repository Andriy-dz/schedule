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
import schedule.dto.request.AudienceRequestDto;
import schedule.dto.response.AudienceResponseDto;
import schedule.model.Audience;
import schedule.service.AudienceService;
import schedule.service.mapper.AudienceMapper;

@RestController
@RequestMapping("/audiences")
public class AudienceController {
    private final AudienceService service;
    private final AudienceMapper mapper;

    public AudienceController(AudienceService service, AudienceMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public AudienceResponseDto add(@RequestBody @Valid AudienceRequestDto dto) {
        return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
    }

    @PostMapping("/add_all")
    public List<AudienceResponseDto> addAll(@RequestBody @Valid List<AudienceRequestDto> dtos) {
        return dtos.stream()
                .map(mapper::mapToModel)
                .map(service::add)
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AudienceResponseDto get(@PathVariable Long id) {
        return mapper.mapToDto(service.get(id));
    }

    @GetMapping
    public List<AudienceResponseDto> getAll(@RequestParam Long universityId) {
        return service.getAllByUnivId(universityId).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public AudienceResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid AudienceRequestDto dto) {
        Audience audience = mapper.mapToModel(dto);
        audience.setId(id);
        return mapper.mapToDto(service.update(audience));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
