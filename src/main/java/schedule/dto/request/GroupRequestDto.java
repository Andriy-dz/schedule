package schedule.dto.request;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class GroupRequestDto {
    @Min(value = 1)
    private Long universityId;
    @NotNull
    @Size(min = 3)
    private String name;
    private List<Long> students;
}
