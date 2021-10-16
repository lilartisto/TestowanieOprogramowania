import entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class StudentRepository {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static final EntityManager em = factory.createEntityManager();

    public void createStudent(String firstName, String lastName, Integer indexNo, String faculty, String courseName, Integer semesterNo) {
        if (firstName == null || lastName == null || indexNo == null || faculty == null || courseName == null || semesterNo == null)
            throw new IllegalArgumentException("Passed values cannot be null");
        Student student = new Student(firstName, lastName, indexNo, faculty, courseName, semesterNo);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(student);
        transaction.commit();
    }

    public Student getById(long id) {
        em.clear();
        return em.find(Student.class, id);
    }

    public void updateFirstName(Student student, String newFirstName) {
        if (student == null || newFirstName == null)
            throw new IllegalArgumentException("Passed values cannot be null");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        student.setFirstName(newFirstName);
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
