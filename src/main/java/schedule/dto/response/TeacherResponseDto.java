package schedule.dto.response;

import lombok.Data;

@Data
public class TeacherResponseDto {
    private Long id;
    private Long universityId;
    private String firstName;
    private String lastName;
    private String subject;
}
