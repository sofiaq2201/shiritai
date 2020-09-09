package com.sofiaq.shiritai.repository;

import com.sofiaq.shiritai.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    
}
