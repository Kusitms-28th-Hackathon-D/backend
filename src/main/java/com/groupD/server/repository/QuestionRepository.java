package com.groupD.server.repository;

import com.groupD.server.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    public List<Question> findAll();
}
