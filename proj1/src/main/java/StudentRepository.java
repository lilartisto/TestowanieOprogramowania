import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import java.util.List;

public class StudentRepository {

    private final EntityManager em;

    public StudentRepository(EntityManager em) {
        this.em = em;
    }

    public void createStudent(String firstName, String lastName, Integer indexNo, String faculty, String courseName, Integer semesterNo) {
        if (firstName == null || lastName == null || indexNo == null || faculty == null || courseName == null || semesterNo == null)
            throw new IllegalArgumentException("Passed values cannot be null");
        Student student = new Student(firstName, lastName, indexNo, faculty, courseName, semesterNo);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(student);
        transaction.commit();
    }

    public void save(Student student) {
        if(student == null){
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (student.getFirstName() == null || student.getLastName() == null || student.getCourseName() == null ||
                student.getFaculty() == null || student.getIndexNo() == null || student.getSemesterNo() == null)
            throw new IllegalArgumentException("All student's attributes cannot be null");
        if (student.getFirstName().strip().equals("")  || student.getFirstName().strip().equals("") ||
                student.getLastName().strip().equals("")  || student.getCourseName().strip().equals("")
                || student.getFaculty().strip().equals("")  ||  student.getIndexNo() < 0 || student.getSemesterNo() < 0)
            throw new IllegalArgumentException("Passed student has to have all valid parameters");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(student);
        transaction.commit();
    }

    public Student getById(long id) {
        em.clear();
        Student student = em.find(Student.class, id);
        if(student == null)
            throw new EntityNotFoundException("No student with id " + id);
        return student;
    }

    public List<Student> getAll() {
        return em.createQuery("Select s from Student s", Student.class).getResultList();
    }

    public void updateStudent(Student student) {
        if(student == null)
            throw new IllegalArgumentException("Passed student cannot be null");
        if (student.getFirstName() == null || student.getLastName() == null || student.getCourseName() == null ||
                student.getFaculty() == null || student.getIndexNo() == null || student.getSemesterNo() == null)
            throw new IllegalArgumentException("All student's attributes cannot be null");
        if (student.getFirstName().strip().equals("")  || student.getFirstName().strip().equals("") ||
                student.getLastName().strip().equals("")  || student.getCourseName().strip().equals("")
                || student.getFaculty().strip().equals("")  ||  student.getIndexNo() < 0 || student.getSemesterNo() < 0)
            throw new IllegalArgumentException("Passed student has to have all valid parameters");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(student);
        transaction.commit();
    }

    public void updateStudent(Student student, String newFirstName, String newLastName, Integer newIndexNo, String newFaculty, String newCourseName, Integer newSemesterNo) {
        if (student == null)
            throw new IllegalArgumentException("Passed student cannot be null");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        student.setFirstName( newFirstName == null ? student.getFirstName() : newFirstName);
        student.setLastName( newLastName == null ? student.getLastName() : newLastName);
        student.setIndexNo( newIndexNo == null ? student.getIndexNo() : newIndexNo);
        student.setFaculty( newFaculty == null ? student.getFaculty() : newFaculty);
        student.setCourseName( newCourseName == null ? student.getCourseName() : newCourseName);
        student.setSemesterNo( newSemesterNo == null ? student.getSemesterNo() : newSemesterNo);
        transaction.commit();
    }

    public void delete(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Passed student cannot be null");
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(student);
            transaction.commit();
    }
}
