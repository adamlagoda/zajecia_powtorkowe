package hibernate.entity;

import java.util.Date;

public class Course {
    private String name;
    private String place;
    private Date startDate;
    private Date endDate;
    private Iterable<Student> students;


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