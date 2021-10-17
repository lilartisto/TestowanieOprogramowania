import org.apache.commons.text.WordUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private Integer indexNo;

    private String faculty;

    private String courseName;

    private Integer semesterNo;

    public Student(String firstName, String lastName, Integer indexNo, String faculty, String courseName, Integer semesterNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNo = indexNo;
        this.faculty = faculty;
        this.courseName = courseName;
        this.semesterNo = semesterNo;
    }

    public Student(){

    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return id == student.id &&
                firstName.equals(student.firstName) &&
                lastName.equals(student.lastName) &&
                indexNo.equals(student.indexNo) &&
                faculty.equals(student.faculty) &&
                courseName.equals(student.courseName) &&
                semesterNo.equals(student.semesterNo);
    }

}