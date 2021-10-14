import controller.Controller;
import entity.Student;


public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();

        Student student = new Student("Mariusz", "Pudzianowski", 3983763, "EITI", "Automatyka i Robotyka", 3);
        Student student1 = new Student("John", "Cena", 123456, "Elektryczny", "Informatyka Stosowana", 5);
        Student studento= new Student("Ewa", "Gonzalez", 2115, "Elektryczny", "Placeholder", 6);

        controller.saveStudent(student);
        controller.saveStudent(student1);
        controller.saveStudent(studento);
        controller.showAllStudents();
        studento.setFirstName("Jonathan");
        controller.updateStudent(studento);
        controller.searchController("firstName", "Ewa");
        controller.deleteStudent(studento);
        controller.searchController("indexNo", 3983763);
        controller.searchController("firstName", "Mariusz");
        controller.searchController("lastName", "Cena");
        controller.searchController("faculty", "elektryczny");
        controller.searchController("firstName", "Jonathan");

        controller.closeSession();
    }

}
