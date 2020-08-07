package hibernate.dao;

import hibernate.entity.Student;

public interface StudentDao {
    Student create(Student student);

    Student find(int id);

    Student get(int id);

    Iterable<Student> findAll();

    void delete(int id);

    void update(Student student);
}
