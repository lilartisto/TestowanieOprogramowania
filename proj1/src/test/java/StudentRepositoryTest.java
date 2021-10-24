import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryTest {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private final EntityManager em = factory.createEntityManager();
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        studentRepository = new StudentRepository(em);
    }

    @Test
    public void shouldReturnAllStudentsWhenDataBaseIsNotEmpty() {
        Student[] students = {
                new Student("Jakub", "Kowalczyk", 729275, "WA", "Architecture", 5),
                new Student("Czeslaw", "Duda", 287464, "WE", "Electricity", 8)
        };

        for (Student student : students) {
            studentRepository.save(student);
        }

        List<Student> studentsFromDatabase = studentRepository.getAll();

        int i = 0;
        for (Student student : studentsFromDatabase) {
            assertEquals(student, students[i]);
            i++;
        }
    }

    @Test
    public void shouldReturnEmptyListWhenDataBaseIsEmpty() {
        List<Student> students = studentRepository.getAll();
        assertTrue(students.isEmpty());
    }

    @Test
    public void shouldAddStudentWhenAddedStudentIsCorrect() {
        Student student = new Student("Stefani", "Germanotta", 123123, "WE", "CompSci", 1);

        assertDoesNotThrow(() -> studentRepository.save(student));
        assertEquals(student, studentRepository.getById(student.getId()));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddedStudentIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentRepository.save(null)
        );
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddedStudentHasEmptyStrings() {
        // 1 empty
        Student student1 = new Student("", "Case", 123123, "MINI", "Math", 3);
        Student student2 = new Student("Test", "", 123124, "MINI", "Math", 3);
        Student student3 = new Student("Test", "Case", 123125, "", "Math", 3);
        Student student4 = new Student("Test", "Case", 123126, "MINI", "", 3);

        // 2 empty
        Student student5 = new Student("", "", 123127, "MINI", "Math", 3);
        Student student6 = new Student("Test", "", 123128, "", "Math", 3);
        Student student7 = new Student("Test", "Case", 123129, "", "", 3);
        Student student8 = new Student("", "Case", 123130, "", "Math", 3);
        Student student9 = new Student("Test", "", 123131, "MINI", "", 3);
        Student student10 = new Student("", "Case", 123132, "MINI", "", 3);

        // 3 empty
        Student student11 = new Student("", "", 123133, "", "Math", 3);
        Student student12 = new Student("", "", 123134, "MINI", "", 3);
        Student student13 = new Student("", "Case", 123135, "", "", 3);
        Student student14 = new Student("Test", "", 123136, "", "", 3);

        // 4 empty
        Student student15 = new Student("", "", 123137, "", "", 3);

        Student[] students = new Student[]{
                student1, student2, student3, student4, student5, student6, student7, student8,
                student9, student10, student11, student12, student13, student14, student15
        };

        for (Student student : students) {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> studentRepository.save(student)
            );
            assertEquals("Passed student has to have all valid parameters", exception.getMessage());
        }
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddedStudentHasNullFields() {
        Student student = new Student(null, null, null, null, null, null);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentRepository.save(student)
        );
        assertEquals("All student's attributes cannot be null", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddedStudentHasNegativeNumbers() {
        Student student1 = new Student("Stefani", "Germanotta", 123123, "WE", "CompSci", -1);
        Student student2 = new Student("Stefani", "Germanotta", -123124, "WE", "CompSci", 1);
        Student student3 = new Student("Stefani", "Germanotta", -123125, "WE", "CompSci", -1);

        Student[] students = new Student[]{ student1, student2, student3 };

        for (Student student : students) {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> studentRepository.save(student)
            );
            assertEquals("Passed student has to have all valid parameters", exception.getMessage());
        }
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddedStudentHasSameIndexNoAsOtherStudent() {
        Student correctStudent = new Student("Maciek", "Kowalczyk", 777555, "WA", "Architecture", 1);
        Student studentWithSameIndex = new Student("Arkadiusz", "Duda", 777555, "WE", "Electricity", 1);

        studentRepository.save(correctStudent);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentRepository.save(studentWithSameIndex)
        );
        assertEquals("Unique index violation", exception.getMessage());
    }

    @Test
    public void shouldSucceedWhenDeletedAnExistingStudent() {
        Student student = new Student("Stefani", "Germanotta", 123123, "WE", "CompSci", 1);

        studentRepository.save(student);
        assertDoesNotThrow(() -> studentRepository.delete(student));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDeletedANonexistentStudent() {
        Student student = new Student("Stefani", "Germanotta", 123123, "WE", "CompSci", 1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentRepository.delete(student)
        );
        assertEquals("There is no such student in the database", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDeletedStudentIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentRepository.delete(null)
        );
        assertEquals("Passed student cannot be null", exception.getMessage());
    }

    @Test
    public void shouldUpdateStudentWhenUpdatedStudentIsCorrect() {
        Student student = new Student("Arkadiusz", "Matczak", 946256, "WE", "Electricity", 3);

        studentRepository.save(student);
        student.setSemesterNo(4);
        studentRepository.updateStudent(student);

        Student updatedStudent = studentRepository.getById(student.getId());

        assertEquals(student, updatedStudent);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUpdatedStudentIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentRepository.updateStudent(null)
        );
        assertEquals("Passed student cannot be null", exception.getMessage());
    }


    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUpdatedStudentHasFewNullFields() {
        Student student1 = new Student("Jacek", "Walesa", 351646, "MINI", "IT", 7);
        Student student2 = new Student("Jacek", "Walesa", 351647, "MINI", "IT", 7);
        Student student3 = new Student("Jacek", "Walesa", 351648, "MINI", "IT", 7);
        Student student4 = new Student("Jacek", "Walesa", 351649, "MINI", "IT", 7);
        Student student5 = new Student("Jacek", "Walesa", 351650, "MINI", "IT", 7);
        Student student6 = new Student("Jacek", "Walesa", 351651, "MINI", "IT", 7);
        Student student7 = new Student("Jacek", "Walesa", 351651, "MINI", "IT", 7);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);

        student1.setSemesterNo(null);
        student2.setCourseName(null);
        student3.setLastName(null);

        student4.setSemesterNo(null);
        student4.setCourseName(null);
        student5.setCourseName(null);
        student5.setLastName(null);
        student6.setSemesterNo(null);
        student6.setLastName(null);

        student7.setSemesterNo(null);
        student7.setCourseName(null);
        student7.setLastName(null);

        Student[] students = new Student[]{ student1, student2, student3, student4, student5, student6, student7 };

        for (Student student : students) {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> studentRepository.updateStudent(student)
            );
            assertEquals("All student's attributes cannot be null", exception.getMessage());
        }
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUpdatedStudentHasFewEmptyStrings() {
        // 1 empty
        Student student1 = new Student("Test", "Case", 123123, "MINI", "Math", 3);
        Student student2 = new Student("Test", "Case", 123124, "MINI", "Math", 3);
        Student student3 = new Student("Test", "Case", 123125, "MINI", "Math", 3);
        Student student4 = new Student("Test", "Case", 123126, "MINI", "Math", 3);

        // 2 empty
        Student student5 = new Student("Test", "Case", 123127, "MINI", "Math", 3);
        Student student6 = new Student("Test", "Case", 123128, "MINI", "Math", 3);
        Student student7 = new Student("Test", "Case", 123129, "MINI", "Math", 3);
        Student student8 = new Student("Test", "Case", 123130, "MINI", "Math", 3);
        Student student9 = new Student("Test", "Case", 123131, "MINI", "Math", 3);
        Student student10 = new Student("Test", "Case", 123132, "MINI", "Math", 3);

        // 3 empty
        Student student11 = new Student("Test", "Case", 123133, "MINI", "Math", 3);
        Student student12 = new Student("Test", "Case", 123134, "MINI", "Math", 3);
        Student student13 = new Student("Test", "Case", 123135, "MINI", "Math", 3);
        Student student14 = new Student("Test", "Case", 123136, "MINI", "Math", 3);

        // 4 empty
        Student student15 = new Student("Test", "Case", 123137, "MINI", "Math", 3);

        Student[] students = new Student[]{
                student1, student2, student3, student4, student5, student6, student7, student8,
                student9, student10, student11, student12, student13, student14, student15
        };

        for (Student student : students) {
            studentRepository.save(student);
        }

        student1.setFirstName("");
        student5.setFirstName("");
        student8.setFirstName("");
        student10.setFirstName("");
        student11.setFirstName("");
        student12.setFirstName("");
        student13.setFirstName("");
        student15.setFirstName("");

        student2.setLastName("");
        student5.setLastName("");
        student6.setLastName("");
        student9.setLastName("");
        student11.setLastName("");
        student12.setLastName("");
        student14.setLastName("");
        student15.setLastName("");

        student3.setFaculty("");
        student6.setFaculty("");
        student7.setFaculty("");
        student8.setFaculty("");
        student11.setFaculty("");
        student13.setFaculty("");
        student14.setFaculty("");
        student15.setFaculty("");

        student4.setCourseName("");
        student7.setCourseName("");
        student9.setCourseName("");
        student10.setCourseName("");
        student12.setCourseName("");
        student13.setCourseName("");
        student14.setCourseName("");
        student15.setCourseName("");

        for (Student student : students) {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> studentRepository.updateStudent(student)
            );
            assertEquals("Passed student has to have all valid parameters", exception.getMessage());
        }
    }

    @Test
    public void shouldUpdateStudentWhenUpdatedStudentHasTheSameInfo() {
        Student student = new Student("Stefani", "Germanotta", 123123, "WE", "CompSci", 1);

        studentRepository.save(student);

        assertDoesNotThrow(() -> studentRepository.updateStudent(student));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUpdatedStudentHasSameIndexAsOtherStudent() {
        Student student = new Student("Michal", "Jackowski", 153064, "MINI", "Math", 2);
        Student studentToUpdate = new Student("Kamil", "Marciniak", 873834, "WA", "Architecture", 5);

        studentRepository.save(student);
        studentRepository.save(studentToUpdate);

        studentToUpdate.setIndexNo(student.getIndexNo());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentRepository.updateStudent(studentToUpdate)
        );
        assertEquals("Unique index violation", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUpdatedStudentHasNegativeIntegers() {
        Student student1 = new Student("Stefani", "Germanotta", 123123, "WE", "CompSci", 1);
        Student student2 = new Student("Stefani", "Germanotta", 123124, "WE", "CompSci", 1);
        Student student3 = new Student("Stefani", "Germanotta", 123125, "WE", "CompSci", 1);

        studentRepository.save(student1);
        studentRepository.save(student2);

        student1.setSemesterNo(-1);

        student2.setIndexNo(-999999);

        student3.setSemesterNo(-1);
        student3.setIndexNo(-999999);

        Student[] students = new Student[]{ student1, student2, student3 };

        for (Student student : students) {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> studentRepository.updateStudent(student)
            );
            assertEquals("Passed student has to have all valid parameters", exception.getMessage());
        }
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUpdatedNonexistentStudent() {
        Student student = new Student("Stefani", "Germanotta", 123123, "WE", "CompSci", 1);

        // No studentRepository.save
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> studentRepository.updateStudent(student)
        );
        //TODO
        assertEquals("No student with id 0", exception.getMessage());
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenReadNonexistentStudent() {
        // The repository is empty
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> studentRepository.getById(1)
        );
        assertEquals("No student with id 1", exception.getMessage());
    }

}
