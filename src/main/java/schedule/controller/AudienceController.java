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
import schedule.dto.request.AudienceRequestDto;
import schedule.dto.response.AudienceResponseDto;
import schedule.exception.CustomException;
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
        try {
            return mapper.mapToDto(service.add(mapper.mapToModel(dto)));
        } catch (Exception e) {
            throw new CustomException("Can`t insert audience - " + dto, e);
        }
    }

    @PostMapping("/add_all")
    public List<AudienceResponseDto> addAll(@RequestBody @Valid List<AudienceRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(mapper::mapToModel)
                    .map(service::add)
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t insert all audiences - " + dtos, e);
        }
    }

    @GetMapping("/{id}")
    public AudienceResponseDto get(@PathVariable Long id) {
        try {
            return mapper.mapToDto(service.get(id));
        } catch (Exception e) {
            throw new CustomException("Can`t get audience by id - " + id, e);
        }
    }

    @GetMapping("/get_all/{uneversityId}")
    public List<AudienceResponseDto> getAll(@PathVariable Long uneversityId) {
        try {
            return service.getAllByUnivId(uneversityId).stream()
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t get all audiences by university id - "
                    + uneversityId, e);
        }
    }

    @PutMapping("/{id}")
    public AudienceResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid AudienceRequestDto dto) {
        try {
            Audience audience = mapper.mapToModel(dto);
            audience.setId(id);
            return mapper.mapToDto(service.update(audience));
        } catch (Exception e) {
            throw new CustomException("Can`t update audience  - " + dto + " by id - " + id, e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can`t delete audience by id - " + id, e);
        }
    }
}
