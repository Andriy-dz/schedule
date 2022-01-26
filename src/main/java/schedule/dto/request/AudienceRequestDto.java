package schedule.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AudienceRequestDto {
    @Min(value = 1)
    private int officeNumber;
    @NotNull
    private Long universityId;
}
