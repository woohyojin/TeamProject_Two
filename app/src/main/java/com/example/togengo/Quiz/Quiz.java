package com.example.togengo.Quiz;

public class Quiz {
    public String question;  //문제 id
    public String problem; //문제
    public String Example; // 보기/예시
    public String answer; // 정답

    public Quiz(String question, String problem, String example, String answer){
          this.question = question;
          this.problem = problem;
          this.Example = example;
          this.answer = answer;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getExample() {
        return Example;
    }

    public void setExample(String example) {
        Example = example;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
