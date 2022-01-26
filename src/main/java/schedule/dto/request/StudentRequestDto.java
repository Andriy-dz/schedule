package schedule.dto.request;

import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequestDto {
    @Size(min = 3)
    private String firstName;
    @Size(min = 3)
    private String lastName;
}
