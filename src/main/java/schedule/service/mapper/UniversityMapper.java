package schedule.service.mapper;

import org.springframework.stereotype.Component;
import schedule.dto.request.UniversityRequestDto;
import schedule.dto.response.UniversityResponseDto;
import schedule.model.University;

@Component
public class UniversityMapper implements ResponseDtoMapper<UniversityResponseDto, University>,
        RequestDtoMapper<UniversityRequestDto, University> {
    @Override
    public University mapToModel(UniversityRequestDto dto) {
        University university = new University();
        university.setCity(dto.getCity());
        university.setName(dto.getName());
        return university;
    }

    @Override
    public UniversityResponseDto mapToDto(University university) {
        UniversityResponseDto dto = new UniversityResponseDto();
        dto.setId(university.getId());
        dto.setCity(university.getCity());
        dto.setName(university.getName());
        return dto;
    }
}
