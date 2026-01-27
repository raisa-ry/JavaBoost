package org.example.multithreading.immutability;

public class Hobby {
    String name;
    int hoursWeekly;

    public Hobby(String name, int hoursWeekly) {
        this.name = name;
        this.hoursWeekly = hoursWeekly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursWeekly() {
        return hoursWeekly;
    }

    public void setHoursWeekly(int hoursWeekly) {
        this.hoursWeekly = hoursWeekly;
    }
}
