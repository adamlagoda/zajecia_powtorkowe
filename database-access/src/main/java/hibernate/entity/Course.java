package hibernate.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;

    @Column(name = "course_name")
    private String name;

    @Column
    private String place;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "course")
    private Set<Student> students = new HashSet<>();


    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}