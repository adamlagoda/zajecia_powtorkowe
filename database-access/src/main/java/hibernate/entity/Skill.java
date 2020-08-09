package hibernate.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer id;

    @Column(name = "skill_name")
    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<Student> students;

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
