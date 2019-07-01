package com.example.myprozhepayanterm;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 7L;
    String username;
    String password;
    byte[] imagebyte ;


    ArrayList<myClass> studentOfMyClasses =new ArrayList<>();
    ArrayList<myClass> teacherOfMyClasses = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }
    public User(String username, String password ,byte[] imagebyte) {
        this.username = username;
        this.password = password;

        this.imagebyte = imagebyte;
    }
    public void teachrsClass(myClass s)
    {
        System.out.println("ssda");
        System.out.println(s.name);
        teacherOfMyClasses.add(s);
        System.out.println("ffff"+ teacherOfMyClasses.size());
    }

   /* public void creatTeacherClass(String name,String room ,String dep, String id)
    {
        myTeacherClass.add(new myClass(name,room,dep,id));
    }public void creaTeacherClass(myClass myclass)
    {
        myClass baba = myclass;
        myTeacherClass.add(baba);
    }*/
}
