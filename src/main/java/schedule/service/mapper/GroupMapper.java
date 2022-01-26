package schedule.service.mapper;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import schedule.dto.request.GroupRequestDto;
import schedule.dto.response.GroupResponseDto;
import schedule.model.Group;
import schedule.model.Student;
import schedule.service.StudentService;
import schedule.service.UniversityService;

@Component
public class GroupMapper implements ResponseDtoMapper<GroupResponseDto, Group>,
        RequestDtoMapper<GroupRequestDto, Group> {
    private final UniversityService universityService;
    private final StudentService studentService;

    public GroupMapper(UniversityService universityService, StudentService studentService) {
        this.universityService = universityService;
        this.studentService = studentService;
    }

    @Override
    public Group mapToModel(GroupRequestDto dto) {
        Group group = new Group();
        group.setName(dto.getName());
        group.setUniversity(universityService.get(dto.getUniversityId()));
        group.setStudents(dto.getStudents()
                .stream()
                .map(studentService::get)
                .collect(Collectors.toList()));
        return group;
    }

    @Override
    public GroupResponseDto mapToDto(Group group) {
        GroupResponseDto dto = new GroupResponseDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setUniversityId(group.getUniversity().getId());
        dto.setStudents(group.getStudents()
                .stream()
                .map(Student::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
