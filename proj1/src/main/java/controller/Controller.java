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

    public void openSession() {
        try {
            if (session == null) {
                session = HibernateUtilities_5.getSessionFactory().openSession();
            }
            transaction = session.beginTransaction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeSession() {
        try {
            HibernateUtilities_5.getSessionFactory().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveStudent(Student student) {
        try {
            if( student == null)
                throw  new NullPointerException("Student cannot be null") ;
            openSession();
            student.toLowercase();
            session.save(student);
            transaction.commit();
            System.out.println("Saved student: " +
                    WordUtils.capitalize(student.getFirstName()) + ' ' +
                    WordUtils.capitalize(student.getLastName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> showAllStudents() {
        try {
            openSession();
            System.out.print("Showing all students\n");
            List<Student> studentList = session.createQuery("from Student", Student.class).list();
            transaction.commit();
            printList(studentList);
            return studentList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateStudent(Student student) {
        try {
            if( student == null)
                throw  new NullPointerException("Student cannot be null") ;
        openSession();
        student.toLowercase();
        session.update(student);
        System.out.println("Student " +
                WordUtils.capitalize(student.getFirstName()) +  " " +
                WordUtils.capitalize(student.getLastName()) + " updated");
        transaction.commit();
        }
        catch(Exception e) {
            System.out.println("There is no such student.");
        }
    }

    public void deleteStudent(Student student) {
        try {
            if( student == null)
                throw  new NullPointerException("Student cannot be null") ;
        openSession();
        student.toLowercase();
        session.delete(student);
        System.out.println("Student " +
                WordUtils.capitalize(student.getFirstName()) +  " " +
                WordUtils.capitalize(student.getLastName()) + " deleted");
        transaction.commit();
        }
        catch(Exception E) {
            System.out.println("There is no such student.");
        }

    }


    //first parameter is attribute name, eg. firstName
    //second parameter is search value, eg. Mariusz
    public List<Student> searchController(String attr, Object value) {
        try {
            if ( attr == null || value == null)
                throw new NullPointerException("Null value has been provided");
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
            printList(studentList);
            return studentList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void printList(List<Student> studentList) {
        studentList.forEach(s -> System.out.print(s.toString() + '\n'));
    }
}
