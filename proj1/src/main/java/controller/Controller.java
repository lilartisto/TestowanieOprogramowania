package controller;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtilities_5;

import java.util.List;

public class Controller {
    Session session = null;
    Transaction transaction = null;

    public void openSession() {
        if (session == null) {
            session = HibernateUtilities_5.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
    }

    public void closeSession() {
        HibernateUtilities_5.getSessionFactory().close();
    }

    public void saveStudent(Student student) {
        openSession();
        student.toLowercase();
        session.save(student);
        transaction.commit();
        System.out.println("Saved student: " + student.getFirstName() + ' ' + student.getLastName());
    }

    public List<Student> showAllStudents() {
        openSession();
        System.out.print("Showing all students\n");
        List<Student> studentList = session.createQuery("from Student", Student.class).list();
        transaction.commit();
        printList(studentList);
        return studentList;
    }


    //first parameter is attribute name, eg. firstName
    //second parameter is search value, eg. Mariusz
    public List<Student> searchController(String attr, Object value) {
        openSession();
        if (value.getClass().equals(String.class))
            value = ((String) value).toLowerCase();
        System.out.print("Searching " + attr + " that is equal to " + value + "\n");
        String query = "from Student where " + attr + "=:" + attr;
        List<Student> studentList = session.createQuery(query, Student.class).setParameter(attr, value).list();
        transaction.commit();
        printList(studentList);
        return studentList;
    }

    public void printList(List<Student> studentList) {
        studentList.forEach(s -> System.out.print(s.toString() + '\n'));
    }
}
