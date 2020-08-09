package jdbc.dao;

import jdbc.configuration.ConnectionFactory;
import jdbc.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

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
        return null;
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
        Course course = new Course("javaPoz21", "Poznań", Date.valueOf("2019-09-14"), Date.valueOf("2020-12-20"));
        dao.create(course);
    }
}
