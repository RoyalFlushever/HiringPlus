package com.tdt.dao;

import com.tdt.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillDao extends JpaRepository<Skill, Long>{
    List<Skill> findByNameIn(List<String> names);
}
