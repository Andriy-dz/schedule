package schedule.dto.response;

import lombok.Data;

@Data
public class LessonResponseDto {
    private Long id;
    private Long groupId;
    private Long audienceId;
    private Long teacherId;
    private String date;
}
