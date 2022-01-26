package schedule.dto.request;

import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class LessonRequestDto {
    @Min(value = 1)
    private Long groupId;
    @Min(value = 1)
    private Long audienceId;
    @Min(value = 1)
    private Long teacherId;
    private String date;
}
