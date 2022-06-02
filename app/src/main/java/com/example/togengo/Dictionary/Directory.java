package com.example.togengo.Dictionary;

import java.util.Objects;

public class Directory {
    public String idx; // 목록 번호
    public String word; // 단어
    public String meaning; // 단어의 뜻
    public String clear;
    public String star;
    public int directoryCheck=0;
    Directory(){}
    Directory(String word){
        this.word = word;
    }

    public int getDirectoryCheck() {
        return directoryCheck;
    }

    public void setDirectoryCheck(int directoryCheck) {
        this.directoryCheck = directoryCheck;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getIdx() {
        return idx;
    }

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public void setIdx(String idx) {
        this.idx = idx;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return Objects.equals(word, directory.word);
    }
    public boolean equalsObj(Directory directory){

        return directory.star.equals(star) || directory.clear.equals(clear);
    }
    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return "Database{" +
                "idx='" + idx + '\'' +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }

    public void setObj(Directory directory) {
        idx = directory.idx;
        word = directory.word;
        meaning = directory.meaning;
        clear = directory.clear;
        star = directory.star;
        directoryCheck = directory.directoryCheck;

    }
}
