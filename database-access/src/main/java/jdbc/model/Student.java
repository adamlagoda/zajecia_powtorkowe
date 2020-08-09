package jdbc.model;

public class Student {
    private String name;
    private int age;
    private Iterable<Skill> skills;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Iterable<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Iterable<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skills=" + skills +
                '}';
    }
}
