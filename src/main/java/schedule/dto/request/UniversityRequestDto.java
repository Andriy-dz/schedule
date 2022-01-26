package schedule.dto.request;

import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UniversityRequestDto {
    @Size(min = 3)
    private String name;
    @Size(min = 3)
    private String city;
}
