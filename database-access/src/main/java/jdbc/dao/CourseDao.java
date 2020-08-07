package jdbc.dao;

import jdbc.model.Course;

public interface CourseDao {
    Course create(Course course);

    Course find(int id);

    Iterable<Course> findAll();

    void delete(int id);

    void update(Course course);
}
