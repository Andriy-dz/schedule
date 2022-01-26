package schedule.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TeacherRequestDto {
    @Min(value = 1)
    private Long universityId;
    @Size(min = 3)
    private String firstName;
    @Size(min = 3)
    private String lastName;
    @Size(min = 3)
    private String subject;
}
