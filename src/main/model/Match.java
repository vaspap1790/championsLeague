package main.model;

import java.time.LocalDate;

public class Match implements Comparable<Match>{

    //Member Variables
    Team homeTeam;
    Team guestTeam;
    int goalsForHTeam;
    int goalsForGTeam;
    MatchDay matchDay;

    //Constructors
    public Match( Team homeTeam, Team guestTeam) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
    }

    public Match(Team homeTeam, Team guestTeam, int goalsForHTeam, int goalsForGTeam) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.goalsForHTeam = goalsForHTeam;
        this.goalsForGTeam = goalsForGTeam;
    }

    public Match(Team homeTeam, Team guestTeam, MatchDay matchDay) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.matchDay = matchDay;
    }

    //Methods
    public void runMatch(int goalsForHTeam, int goalsForGTeam){

        homeTeam.setGoalsFor(homeTeam.getGoalsFor() + goalsForHTeam);
        homeTeam.setGoalsAgainst(homeTeam.getGoalsAgainst() + goalsForGTeam);

        guestTeam.setGoalsFor(guestTeam.getGoalsFor() + goalsForGTeam);
        guestTeam.setGoalsAgainst(guestTeam.getGoalsAgainst() + goalsForHTeam);

        homeTeam.setGamesPlayed(homeTeam.getGamesPlayed() + 1);
        guestTeam.setGamesPlayed(guestTeam.getGamesPlayed() + 1);

        if(goalsForHTeam > goalsForGTeam){
            homeTeam.setGamesWon(homeTeam.getGamesWon() + 1);
            guestTeam.setGamesLost(guestTeam.getGamesLost() + 1);
        }

        if(goalsForHTeam < goalsForGTeam){
            guestTeam.setGamesWon(guestTeam.getGamesWon() + 1);
            homeTeam.setGamesLost(homeTeam.getGamesLost() + 1);
        }

        if(goalsForHTeam == goalsForGTeam){
            guestTeam.setGamesDrawn(guestTeam.getGamesDrawn() + 1);
            homeTeam.setGamesDrawn(homeTeam.getGamesDrawn() + 1);
        }
    }

    //Getters-Setters
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

    public MatchDay getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }

    @Override
    public String toString() {
        return  matchDay.getDate().toString() + " " + homeTeam + " " + goalsForHTeam + " - " + goalsForGTeam + " " + guestTeam;
    }

    @Override
    public int compareTo(Match o) {
        return 0;
    }
}
