package com.ajeet.quizapp.controller;

import com.ajeet.quizapp.model.Question;
import com.ajeet.quizapp.model.QuestionWrapper;
import com.ajeet.quizapp.model.QuizResponse;
import com.ajeet.quizapp.service.QuizService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable Integer id) {
        return quizService.getQuizQuestionsById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> quizResponse) {
        return quizService.submitQuiz(id, quizResponse);
    }
}
