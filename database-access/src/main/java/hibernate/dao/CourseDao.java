package hibernate.dao;

import hibernate.entity.Course;

import java.util.Date;

public interface CourseDao {
    void deleteFinishedBefore(Date finishedDate);

    Iterable<Course> findByCity(String city);
}
