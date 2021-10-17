import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
        EntityManager em = factory.createEntityManager();
        StudentRepository repo = new StudentRepository(em);
        repo.createStudent("Mariusz", "Pudzianowski", 3983763, "EITI", "Automatyka i Robotyka", 3);
        repo.createStudent("Lisek", "Ogrodniczek", 3983543, "Elektryczny", "Informatyka Stosowana", 4);

        Student yep = new Student("HAH", "AHAH", 123, "Elektryczny", "Informatyka Stosowana", 4);
        Student tst = repo.getById(1);
        tst.setSemesterNo(2115);
        tst.setFirstName("JONATHAN");
        repo.updateStudent(tst);
        System.out.println(repo.getById(1));
        repo.delete(repo.getById(1));
        System.out.println(repo.getById(2));
    }

}
