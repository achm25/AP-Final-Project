package com.example.myprozhepayanterm;

public class RecyclerItem {

    String name;
    String dec;

    public RecyclerItem(String name, String dec) {
        this.name = name;
        this.dec = dec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}
