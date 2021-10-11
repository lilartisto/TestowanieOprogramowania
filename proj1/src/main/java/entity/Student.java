package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Table
@Entity
public class Student implements Serializable {
    @Id
//    @Column(name = "id", nullable = false)
    private UUID id;

//    @Column(name = "first_name")
    private String firstName;

//    @Column(name = "last_name")
    private String lastName;

//    @Column(name = "index_no")
    private Integer indexNo;

//    @Column(name = "faculty")
    private String faculty;

//    @Column(name = "course_name")
    private String courseName;

//    @Column(name = "semester_no")
    private Integer semesterNo;

    public Student(String firstName, String lastName, Integer indexNo, String faculty, String courseName, Integer semesterNo) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNo = indexNo;
        this.faculty = faculty;
        this.courseName = courseName;
        this.semesterNo = semesterNo;
    }

    public Student() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }

    @Override
    public String toString() {
        return "Student{" + "\n" +
                "\t" + "id = " + id + "\n" +
                "\t" + "firstName = " + firstName + "\n" +
                "\t" + "lastName = " + lastName + "\n" +
                "\t" + "indexNo = " + indexNo + "\n" +
                "\t" + "faculty = " + faculty + "\n" +
                "\t" + "courseName = " + courseName + "\n" +
                "\t" + "semesterNo = " + semesterNo + "\n" +
                '}';
    }
}