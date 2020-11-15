package main.model;

import java.time.LocalDate;

public enum MatchDay {

    MatchDay1(1),
    MatchDay2(2),
    MatchDay3(3),
    MatchDay4(4),
    MatchDay5(5),
    MatchDay6(6),
    QuarterFinals1(7),
    QuarterFinals2(8),
    SemiFinals1(9),
    SemiFinals2(10),
    Final(11);

    //Member Variables
    private final int count;
    private LocalDate date;

    //Constructor
    MatchDay(int count) {
        this.count = count;
    }

    //Getters-Setters
    public int getCount() {
        return count;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
