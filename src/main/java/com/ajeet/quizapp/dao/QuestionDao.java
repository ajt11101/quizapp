package com.ajeet.quizapp.dao;

import com.ajeet.quizapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> getQuestionsByCategoryIgnoreCase(String category);

}
