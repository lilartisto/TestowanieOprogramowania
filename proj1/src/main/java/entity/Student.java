package entity;

import org.apache.commons.text.WordUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Table
@Entity
public class Student implements Serializable {
    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private Integer indexNo;

    private String faculty;

    private String courseName;

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
                "\t" + "firstName = " + WordUtils.capitalizeFully(firstName) + "\n" +
                "\t" + "lastName = " + WordUtils.capitalizeFully(lastName) + "\n" +
                "\t" + "indexNo = " + indexNo + "\n" +
                "\t" + "faculty = " + WordUtils.capitalizeFully(faculty) + "\n" +
                "\t" + "courseName = " + WordUtils.capitalizeFully(courseName) + "\n" +
                "\t" + "semesterNo = " + semesterNo + "\n" +
                '}';
    }

    public void toLowercase(){
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.faculty = faculty.toLowerCase();
        this.courseName = courseName.toLowerCase();
    }
}