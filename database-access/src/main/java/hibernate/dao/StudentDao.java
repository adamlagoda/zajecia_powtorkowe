package hibernate.dao;

import hibernate.entity.Student;

import java.util.Set;

public interface StudentDao {
    Student create(Student student);

    Student find(int id);

    Student get(int id);

    Set<Student> findAll();

    void delete(int id);

    void update(Student student);
}
