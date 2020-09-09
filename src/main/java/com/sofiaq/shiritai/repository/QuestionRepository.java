package com.sofiaq.shiritai.repository;

import com.sofiaq.shiritai.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
