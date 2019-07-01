package com.example.myprozhepayanterm;

import java.util.ArrayList;

public class Class {
    String name;
    String room;
    String dep;
    String id;

    ArrayList<User> userOfClass = new ArrayList<>();
    public Class(String name, String room, String dep, String id) {
        this.name = name;
        this.room = room;
        this.dep = dep;
        this.id = id;
    }
    public Class(String name, String room, String dep, String id,ArrayList<User> userOfClass) {
        this.name = name;
        this.room = room;
        this.dep = dep;
        this.id = id;
        this.userOfClass = userOfClass;
    }
}
