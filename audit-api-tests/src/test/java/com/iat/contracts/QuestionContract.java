package com.iat.contracts;


public class QuestionContract {

    public String questionsPath() {
        return "/api/questions";
    }

    public String questionsByIdPath(String id) {
        return "/api/questions/" + id;
    }

}
