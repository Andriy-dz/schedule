package schedule.service.mapper;

import org.springframework.stereotype.Component;
import schedule.dto.request.StudentRequestDto;
import schedule.dto.response.StudentResponseDto;
import schedule.model.Student;
import schedule.service.GroupService;

@Component
public class StudentMapper implements ResponseDtoMapper<StudentResponseDto, Student>,
        RequestDtoMapper<StudentRequestDto, Student> {
    private final GroupService groupService;

    public StudentMapper(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public Student mapToModel(StudentRequestDto dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        return student;
    }

    @Override
    public StudentResponseDto mapToDto(Student student) {
        StudentResponseDto dto = new StudentResponseDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        return dto;
    }
}
