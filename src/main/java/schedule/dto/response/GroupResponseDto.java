package schedule.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class GroupResponseDto {
    private Long id;
    private Long universityId;
    private String name;
    private List<Long> students;
}
