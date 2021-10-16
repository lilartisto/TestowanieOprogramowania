public class Main {

    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository();
        repo.createStudent("Mariusz", "Pudzianowski", 3983763, "EITI", "Automatyka i Robotyka", 3);
        repo.createStudent("Lisek", "Ogrodniczek", 3983543, "Elektryczny", "Informatyka Stosowana", 4);
        repo.updateFirstName(repo.getById(1), "Jacek");
        repo.delete(repo.getById(1));
        System.out.println(repo.getById(1));
        repo.updateStudent(repo.getById(2), null, null, null, "MiNI", null, null);
        System.out.println(repo.getById(2));
    }

}
