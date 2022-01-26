package schedule.service.mapper;

import org.springframework.stereotype.Component;
import schedule.dto.request.TeacherRequestDto;
import schedule.dto.response.TeacherResponseDto;
import schedule.model.Teacher;
import schedule.service.UniversityService;

@Component
public class TeacherMapper implements RequestDtoMapper<TeacherRequestDto, Teacher>,
        ResponseDtoMapper<TeacherResponseDto, Teacher> {
    private final UniversityService universityService;

    public TeacherMapper(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public Teacher mapToModel(TeacherRequestDto dto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setSubject(dto.getSubject());
        teacher.setUniversity(universityService.get(dto.getUniversityId()));
        return teacher;
    }

    @Override
    public TeacherResponseDto mapToDto(Teacher teacher) {
        TeacherResponseDto dto = new TeacherResponseDto();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setSubject(teacher.getSubject());
        dto.setUniversityId(teacher.getUniversity().getId());
        return dto;
    }
}
