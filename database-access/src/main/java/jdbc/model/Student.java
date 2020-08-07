package jdbc.model;

public class Student {
    private String name;
    private int age;
    private Iterable<Skill> skills;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skills=" + skills +
                '}';
    }
}
