package com.example.courseapp;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<Question> questions;
    private int correct;
    private int incorrect;
    private int totalQuestions;
    private int score;
    private Question currentQuestions;

    public Quiz(){
        correct = 0;
        incorrect = 0;
        totalQuestions = 0;
        score = 0;
        currentQuestions = new Question(10);
        questions = new ArrayList<Question>();
    }

    public void makeQuestion(){
        currentQuestions = new Question(totalQuestions * 2 + 5);
        totalQuestions++;
        questions.add(currentQuestions);
    }

    public boolean checkAnswer(int sumbAnswer){
        boolean isCorrect;
        if(currentQuestions.getAnswer() == sumbAnswer){
            correct++;
            isCorrect = true;
        }else {
            incorrect++;
            isCorrect = false;
        }
        score = correct * 10 - incorrect * 30;
        return isCorrect;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrentQuestions() {
        return currentQuestions;
    }

    public void setCurrentQuestions(Question currentQuestions) {
        this.currentQuestions = currentQuestions;
    }
}
