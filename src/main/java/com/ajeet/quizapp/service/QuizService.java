package com.ajeet.quizapp.service;

import com.ajeet.quizapp.dao.QuestionDao;
import com.ajeet.quizapp.dao.QuizDao;
import com.ajeet.quizapp.model.Question;
import com.ajeet.quizapp.model.QuestionWrapper;
import com.ajeet.quizapp.model.Quiz;
import com.ajeet.quizapp.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUsers = new ArrayList<>();
        for (Question q : questionsFromDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUsers.add(qw);
        }
        return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<QuizResponse> quizResponse) {
        Optional<Quiz> quizOpt = quizDao.findById(id);
        if (quizOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quiz quiz = quizOpt.get();
        List<Question> questions = quiz.getQuestions();
        Map<Integer, String> answerMap = new HashMap<>();
        for (Question q : questions) {
            answerMap.put(q.getId(), q.getRightAnswer());
        }
        int right = 0;
        for(QuizResponse qr : quizResponse) {
            String correctAnswer = answerMap.get(qr.getId());
            if (correctAnswer != null && qr.getAnswer() != null && qr.getAnswer().equals(correctAnswer)) {
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
