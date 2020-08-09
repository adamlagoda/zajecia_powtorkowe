package hibernate.dao;

import hibernate.configuration.Configuration;
import hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private final Configuration configuration;
    private static final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    public StudentDaoImpl() {
        this.configuration = new Configuration();
    }

    @Override
    public Student create(Student student) {
        return null;
    }
//merge - do update'a
    //remove - do delete'a
    @Override
    public Student find(int id) {
        EntityManager em = null;
        Student found = null;
        try {
            em = configuration.getEntityManager();
            found =  em.find(Student.class, id);
            found.getSkills();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return found;
    }

    @Override
    public Student get(int id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        EntityManager em = null;
        List<Student> found = null;
        try {
            em = configuration.getEntityManager();
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            found = query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return found;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Student student) {

    }

    public static void main(String[] args) {
        StudentDao dao = new StudentDaoImpl();
        List<Student> student = dao.findAll();
        logger.info(student.toString());
    }
}
