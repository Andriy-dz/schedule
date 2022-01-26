package schedule.service.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import schedule.dto.request.LessonRequestDto;
import schedule.dto.response.LessonResponseDto;
import schedule.model.Lesson;
import schedule.service.AudienceService;
import schedule.service.GroupService;
import schedule.service.TeacherService;
import schedule.unit.DateTimePatternUtil;

@Component
public class LessonMapper implements RequestDtoMapper<LessonRequestDto, Lesson>,
        ResponseDtoMapper<LessonResponseDto, Lesson> {
    private final AudienceService audienceService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final DateTimeFormatter pattern =
            DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_TIME_PATTERN);

    public LessonMapper(AudienceService audienceService,
                        TeacherService teacherService,
                        GroupService groupService) {
        this.audienceService = audienceService;
        this.teacherService = teacherService;
        this.groupService = groupService;
    }

    @Override
    public Lesson mapToModel(LessonRequestDto dto) {
        Lesson lesson = new Lesson();
        lesson.setAudience(audienceService.get(dto.getAudienceId()));
        lesson.setTeacher(teacherService.get(dto.getTeacherId()));
        lesson.setGroup(groupService.get(dto.getGroupId()));
        lesson.setDate(LocalDateTime.parse(dto.getDate(), pattern));
        return lesson;
    }

    @Override
    public LessonResponseDto mapToDto(Lesson lesson) {
        LessonResponseDto dto = new LessonResponseDto();
        dto.setId(lesson.getId());
        dto.setAudienceId(lesson.getAudience().getId());
        dto.setTeacherId(lesson.getTeacher().getId());
        dto.setGroupId(lesson.getGroup().getId());
        dto.setDate(lesson.getDate().format(pattern));
        return dto;
    }
}
