package controller;

import entity.Student;
import org.apache.commons.text.WordUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtilities_5;

import java.util.List;

public class Controller {
    Session session = null;
    Transaction transaction = null;

    public Controller() {
        openSession();
    }

    private void openSession() {
        if (session == null) {
            session = HibernateUtilities_5.getSessionFactory().openSession();
            transaction = session.beginTransaction();
        }
//        transaction.
        System.out.print(transaction.getStatus());
    }

    public void closeSession() {
        try {
            session.close();
//            HibernateUtilities_5.getSessionFactory().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveStudent(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Student cannot be null");
        openSession();
        student.toLowercase();
        session.save(student);
        transaction.commit();
        closeSession();
    }

    public List<Student> showAllStudents() {
        openSession();
        System.out.print("Showing all students\n");
        List<Student> studentList = session.createQuery("from Student", Student.class).list();
//        transaction.commit();
        closeSession();
        return studentList;
    }

    public void updateStudent(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Student cannot be null");
        openSession();
        student.toLowercase();
        session.update(student);
        closeSession();
        transaction.commit();
    }

    public void deleteStudent(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Student cannot be null");
        openSession();
        student.toLowercase();
        session.delete(student);
        transaction.commit();
        closeSession();
    }


    //first parameter is attribute name, eg. firstName
    //second parameter is search value, eg. Mariusz
    public List<Student> searchController(String attr, Object value) {
        if (attr == null || value == null)
            throw new IllegalArgumentException("Null value has been provided");
        openSession();
        if (value.getClass().equals(String.class)) {
            value = ((String) value).toLowerCase();
            System.out.print("Searching " + attr +
                    " that is equal to " + WordUtils.capitalizeFully((String) value) + "\n");
        } else
            System.out.print("Searching " + attr +
                    " that is equal to " + value + "\n");

        String query = "from Student where " + attr + "=:" + attr;
        List<Student> studentList = session.createQuery(query, Student.class).setParameter(attr, value).list();
        transaction.commit();
        closeSession();
        return studentList;
    }
}
