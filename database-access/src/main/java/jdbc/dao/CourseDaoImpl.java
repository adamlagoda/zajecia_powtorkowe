package jdbc.dao;

import jdbc.configuration.ConnectionFactory;
import jdbc.model.Course;
import jdbc.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CourseDaoImpl implements CourseDao {
    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
    private final ConnectionFactory connectionFactory;

    public CourseDaoImpl() {
        this.connectionFactory = new ConnectionFactory("database.properties");
    }

    @Override
    public Course create(Course course) {
        Course created = null;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO courses (course_name, place, start_date, end_date) VALUES (?, ?, ?, ?)");
             Statement selectId = connection.createStatement()) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getPlace());
            statement.setDate(3, course.getStartDate());
            statement.setDate(4, course.getEndDate());
            statement.executeUpdate();
            logger.info("Student created");
            ResultSet resultSet = selectId.executeQuery("SELECT course_id FROM courses ORDER BY course_id DESC LIMIT 0,1");
            resultSet.next();
            int courseId = resultSet.getInt(1);
            created = new Course(courseId, course.getName(), course.getPlace(), course.getStartDate(), course.getEndDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return created;
    }

    @Override
    public Course find(int id) {
        Course created = null;
        Set<Student> studentSet = new HashSet<>();
        try (Connection connection = connectionFactory.getConnection();
             Statement select = connection.createStatement()) {
            ResultSet resultSet = select.executeQuery("SELECT * FROM courses c LEFT JOIN students s on c.course_id = s.course_id ");
            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String name = resultSet.getString("course_name");
                String place = resultSet.getString("place");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                if (created == null) {
                    created = new Course(courseId, name, place, startDate, endDate);
                }
                String studentName = resultSet.getString("student_name");
                int age = resultSet.getInt("age");
                Student student = new Student(studentName, age);
                studentSet.add(student);
                created.getStudents().add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Course found");
        return created;
    }

    @Override
    public Iterable<Course> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Course course) {

    }

    public static void main(String[] args) {
        CourseDao dao = new CourseDaoImpl();
        Course course = dao.find(1);
        logger.info(course.toString());
    }
}
