package com.athtech.model;

import java.time.LocalDate;

public class Match {

    //Member Variables
    LocalDate matchDate;
    Team homeTeam;
    Team guestTeam;
    int goalsForHTeam;
    int goalsForGTeam;

    //Constructors
    public Match(LocalDate matchDate, Team homeTeam, Team guestTeam) {
        this.matchDate = matchDate;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
    }

    //Getters-Setters
    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public int getGoalsForHTeam() {
        return goalsForHTeam;
    }

    public void setGoalsForHTeam(int goalsForHTeam) {
        this.goalsForHTeam = goalsForHTeam;
    }

    public int getGoalsForGTeam() {
        return goalsForGTeam;
    }

    public void setGoalsForGTeam(int goalsForGTeam) {
        this.goalsForGTeam = goalsForGTeam;
    }

    @Override
    public String toString() {
        return  matchDate + " " + homeTeam + " " + goalsForHTeam + " - " + goalsForGTeam + " " + guestTeam;
    }
}
