public class Main {

    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository();
        repo.createStudent("Mariusz", "Pudzianowski", 3983763, "EITI", "Automatyka i Robotyka", 3);
        System.out.println(repo.getById(1));
    }

}
