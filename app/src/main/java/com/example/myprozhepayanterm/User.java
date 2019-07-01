package com.example.myprozhepayanterm;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 7829136421241571165L;
    String username;
    String password;
    byte[] imagebyte ;

    ArrayList<Class> myClass = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }
    public User(String username, String password ,byte[] imagebyte) {
        this.username = username;
        this.password = password;

        this.imagebyte = imagebyte;
    }

    public void creatClass(String name,String room ,String dep, String id)
    {
        myClass.add(new Class(name,room,dep,id));
    }public void creatClass(Class temp)
    {
        myClass.add(temp);
    }
}
