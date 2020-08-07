package hibernate.entity;

public class Skill {
    private String name;
    private Iterable<Student> students;

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
