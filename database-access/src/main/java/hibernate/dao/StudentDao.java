package hibernate.dao;

import hibernate.entity.Student;

import java.util.List;

public interface StudentDao {
    Student create(Student student);

    Student find(int id);

    Student get(int id);

    List<Student> findAll();

    void delete(int id);

    void update(Student student);
}
