package schedule.service.mapper;

import org.springframework.stereotype.Component;
import schedule.dto.request.AudienceRequestDto;
import schedule.dto.response.AudienceResponseDto;
import schedule.model.Audience;
import schedule.service.UniversityService;

@Component
public class AudienceMapper implements RequestDtoMapper<AudienceRequestDto, Audience>,
        ResponseDtoMapper<AudienceResponseDto, Audience> {
    private final UniversityService universityService;

    public AudienceMapper(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public Audience mapToModel(AudienceRequestDto dto) {
        Audience audience = new Audience();
        audience.setOfficeNumber(dto.getOfficeNumber());
        audience.setUniversity(universityService.get(dto.getUniversityId()));
        return audience;
    }

    @Override
    public AudienceResponseDto mapToDto(Audience audience) {
        AudienceResponseDto dto = new AudienceResponseDto();
        dto.setId(audience.getId());
        dto.setOfficeNumber(audience.getOfficeNumber());
        dto.setUniversityId(audience.getUniversity().getId());
        return dto;
    }
}
