package com.example.myprozhepayanterm;

import java.io.Serializable;

public class Homework implements Serializable {
    private static final long serialVersionUID = 9L;
String name;
String score;
String Time;
String dep;
String Topic;

    public Homework(String name, String score, String time, String dep, String topic) {
        this.name = name;
        this.score = score;
        Time = time;
        this.dep = dep;
        Topic = topic;
    }

    public Homework(String name, String score, String time, String dep) {
        this.name = name;
        this.score = score;
        Time = time;
        this.dep = dep;
    }
}
