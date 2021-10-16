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
}
