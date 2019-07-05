package com.example.myprozhepayanterm;

import java.io.Serializable;

public class AnswerOfHomework implements Serializable {
    private static final long serialVersionUID = 11L;

    byte[] imagebyte ;
    String classId;
    String nameOfUser;
    String answer;
    String idAnswer;

    public AnswerOfHomework(byte[] imagebyte, String classId, String nameOfUser, String idAnswer) {
        this.imagebyte = imagebyte;
        this.classId = classId;
        this.nameOfUser = nameOfUser;
        this.idAnswer = idAnswer;
    }

    public AnswerOfHomework(String classId, String nameOfUser, String answer, String idAnswer) {
        this.classId = classId;
        this.nameOfUser = nameOfUser;
        this.answer = answer;
        this.idAnswer = idAnswer;
    }
}
