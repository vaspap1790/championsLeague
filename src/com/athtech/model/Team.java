package com.athtech.model;

public class Team {

    //Member Variables
    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrawn;
    private int goalsFor;
    private int goalsAgainst;
    private QualificationCode qualificationCode;

    //Constructor
    public Team(String name) {
        this.name = name;
        this.qualificationCode = QualificationCode.FirstRound;
    }

    //Methods
    public int getPoints(){
        return 3*gamesWon + gamesDrawn;
    }

    public int getGoalDifference(){
        return goalsFor-goalsAgainst;
    }

    //Getters-Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public void setGamesDrawn(int gamesDrawn) {
        this.gamesDrawn = gamesDrawn;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public QualificationCode getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(QualificationCode qualificationCode) {
        this.qualificationCode = qualificationCode;
    }
}
