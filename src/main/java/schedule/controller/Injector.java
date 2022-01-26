package schedule.controller;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.model.Audience;
import schedule.model.Group;
import schedule.model.Lesson;
import schedule.model.Student;
import schedule.model.Teacher;
import schedule.model.University;
import schedule.service.AudienceService;
import schedule.service.GroupService;
import schedule.service.LessonService;
import schedule.service.StudentService;
import schedule.service.TeacherService;
import schedule.service.UniversityService;

@RestController
public class Injector {
    private final UniversityService universityService;
    private final AudienceService audienceService;
    private final GroupService groupService;
    private final LessonService lessonService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public Injector(UniversityService universityService,
                    AudienceService audienceService,
                    GroupService groupService,
                    LessonService lessonService,
                    StudentService studentService,
                    TeacherService teacherService) {
        this.universityService = universityService;
        this.audienceService = audienceService;
        this.groupService = groupService;
        this.lessonService = lessonService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/inject")
    public String inject() {
        University university = new University();
        university.setName("SomeName");
        university.setCity("SomeCity");
        universityService.add(university);

        Audience audience1 = new Audience();
        audience1.setUniversity(university);
        audience1.setOfficeNumber(1);
        Audience audience2 = new Audience();
        audience2.setUniversity(university);
        audience2.setOfficeNumber(2);
        Audience audience3 = new Audience();
        audience3.setUniversity(university);
        audience3.setOfficeNumber(3);
        audienceService.add(audience1);
        audienceService.add(audience2);
        audienceService.add(audience3);

        Teacher teacherOne = new Teacher();
        teacherOne.setFirstName("TeacherOne");
        teacherOne.setLastName("TeacherOne");
        teacherOne.setSubject("Math");
        teacherOne.setUniversity(university);
        Teacher teacherTwo = new Teacher();
        teacherTwo.setFirstName("TeacherTwo");
        teacherTwo.setLastName("TeacherTwo");
        teacherTwo.setSubject("England");
        teacherTwo.setUniversity(university);
        teacherService.add(teacherOne);
        teacherService.add(teacherTwo);

        Student student1 = new Student();
        student1.setFirstName("Student1");
        student1.setLastName("Student1");
        Student student2 = new Student();
        student2.setFirstName("Student2");
        student2.setLastName("Student2");
        Student student3 = new Student();
        student3.setFirstName("Student3");
        student3.setLastName("Student3");
        studentService.add(student1);
        studentService.add(student2);
        studentService.add(student3);

        Group group1 = new Group();
        group1.setUniversity(university);
        group1.setName("Group1");
        group1.getStudents().add(student1);
        group1.getStudents().add(student2);
        Group group2 = new Group();
        group2.setUniversity(university);
        group2.setName("Group2");
        group2.getStudents().add(student3);
        groupService.add(group1);
        groupService.add(group2);

        Lesson lesson1 = new Lesson();
        lesson1.setAudience(audience1);
        lesson1.setGroup(group1);
        lesson1.setTeacher(teacherOne);
        lesson1.setDate(LocalDateTime.of(2022, 1, 1, 9, 0));
        Lesson lesson2 = new Lesson();
        lesson2.setAudience(audience2);
        lesson2.setGroup(group2);
        lesson2.setTeacher(teacherTwo);
        lesson2.setDate(LocalDateTime.of(2022, 1, 1, 9, 0));
        lessonService.add(lesson1);
        lessonService.add(lesson2);
        return "data created";
    }
}
