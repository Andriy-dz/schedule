package schedule.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import schedule.dto.response.AudienceResponseDto;
import schedule.model.Audience;
import schedule.model.University;
import schedule.service.UniversityService;

class AudienceMapperTest {

    private AudienceMapper audienceMapper;

    @BeforeEach
    void setUp() {
        UniversityService service = Mockito.mock(UniversityService.class);
        audienceMapper = new AudienceMapper(service);
    }

    @Test
    void mapToDto_ok() {
        University university = Mockito.mock(University.class);
        Audience audience = new Audience();
        audience.setId(1L);
        audience.setOfficeNumber(1);
        audience.setUniversity(university);
        AudienceResponseDto expected = new AudienceResponseDto();
        expected.setId(1L);
        expected.setOfficeNumber(1);
        expected.setUniversityId(university.getId());
        assertEquals(expected, audienceMapper.mapToDto(audience));
    }

//    @Test
//    void dtoToModel_ok() {
//        A
//    }
}