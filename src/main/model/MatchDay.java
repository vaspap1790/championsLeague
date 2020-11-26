package main.model;

import java.time.LocalDate;

public enum MatchDay {

    MatchDay1,
    MatchDay2,
    MatchDay3,
    MatchDay4,
    MatchDay5,
    MatchDay6,
    QuarterFinals1,
    QuarterFinals2,
    QuarterFinals3,
    QuarterFinals4,
    SemiFinals1,
    SemiFinals2,
    SemiFinals3,
    SemiFinals4,
    Finals;

    //Member Variables
    private LocalDate date;

    //Getters-Setters

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
