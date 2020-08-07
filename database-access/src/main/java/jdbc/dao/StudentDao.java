package jdbc.dao;

import jdbc.model.Skill;

public interface StudentDao {
    void deleteNotOwningSkill(Skill skill);
    void updateSkills(int id, Iterable<Skill> skills);
}
