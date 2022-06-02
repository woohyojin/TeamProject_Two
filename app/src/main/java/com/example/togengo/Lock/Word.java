package com.example.togengo.Lock;

public class Word {

    public String idx; // 항목
    public String division; // 분류
    public String word; // 문자
    public String meaning; // 의미


    // TODO : get,set 함수 생략


    @Override
    public String toString() {
        return "User{" +
                "idx='" + idx + '\'' +
                ", division='" + division + '\'' +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }


}
