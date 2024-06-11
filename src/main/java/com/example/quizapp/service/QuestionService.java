package com.example.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<> (questionDao.findAll(),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        // if everything goes wrong then print empty List
        return new ResponseEntity<> ( new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return  new ResponseEntity<> (questionDao.findByCategory(category),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        // if everything goes wrong then print empty List
        return new ResponseEntity<> ( new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        try{
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        // if unauthorized person wants to make ques don't allow
        return new ResponseEntity<> ( new String(),HttpStatus.BAD_REQUEST);

    }
}
