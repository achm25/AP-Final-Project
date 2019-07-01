package com.example.myprozhepayanterm;

import java.io.Serializable;
import java.util.ArrayList;

public class myClass implements Serializable {
    private static final long serialVersionUID = 5L;
    String name;
    String room;
    String dep;
    String id;
    ArrayList<User> teacherOfClass = new ArrayList<>();
    ArrayList<User> userOfClass = new ArrayList<>();
    public myClass(String name, String room, String dep, String id, User user) {
        this.name = name;
        this.room = room;
        this.dep = dep;
        this.id = id;
        teacherOfClass.add(user);
    }
    public myClass(String name, String room, String dep, String id) {
        this.name = name;
        this.room = room;
        this.dep = dep;
        this.id = id;

    }
    public int tedadUser()
    {
        return userOfClass.size();
    }
    public void addUser(User u)
    {
        userOfClass.add(u);
    }public void addTeacher(User u)
    {
        teacherOfClass.add(u);
    }

}
