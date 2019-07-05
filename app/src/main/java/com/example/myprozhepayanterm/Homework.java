package com.example.myprozhepayanterm;

import java.io.Serializable;
import java.util.ArrayList;

public class Homework implements Serializable {
    private static final long serialVersionUID = 9L;
String name;
String score;
String Time;
String Topic;
String date;
String dep;
ArrayList<User> teacherOfHomework = new ArrayList<>();
 ArrayList<User> userOfHomework = new ArrayList<>();
byte[] imagebyte ;
boolean depIsEmpty = true;
String teacherScore =null;
String comment =null;
String classID;
ArrayList<AnswerOfHomework> studentAnswer = new ArrayList<>();

    public Homework(String name, String score, String time, String topic, String date, String dep,byte[] imagebyte) {
        this.name = name;
        this.score = score;
        Time = time;
        Topic = topic;
        this.date = date;
        this.dep = dep;
        this.imagebyte = imagebyte;
    }

    public Homework(String name, String score, String time, String topic, String date,byte[] imagebyte) {
        this.name = name;
        this.score = score;
        Time = time;
        Topic = topic;
        this.date = date;
        this.imagebyte = imagebyte;
    }
}
