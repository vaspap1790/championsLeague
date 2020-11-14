package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team implements Comparable<Team>{

    //Member Variables
    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrawn;
    private int goalsFor;
    private int goalsAgainst;
    private QualificationCode qualificationCode;
    private List<Match> matches;

    //Constructors
    public Team(String name) {
        this.name = name;
        this.qualificationCode = QualificationCode.FirstRound;
        this.matches = new ArrayList<>();
    }

    public Team() {
    }

    //Methods
    public int getPoints(){
        return 3*gamesWon + gamesDrawn;
    }

    public int getGoalDifference(){
        return goalsFor-goalsAgainst;
    }

    public int resolveEqualRanking(Team team){

        int teamGoals = 0;
        int oppositeTeamGoals = 0;

        for (Match match : getAgainstMatches(team)){
            if(match.getHomeTeam().equals(team)){
                oppositeTeamGoals += match.goalsForHTeam;
                teamGoals += match.goalsForGTeam;
            }else{
                oppositeTeamGoals += match.goalsForGTeam;
                teamGoals += match.goalsForHTeam;
            }
        }

        if (teamGoals == oppositeTeamGoals){
            return Integer.compare(getGoalsFor(), team.getGoalsFor());
        }
        else{
            return Integer.compare(teamGoals, oppositeTeamGoals);
        }
    }

    public List<Match> getAgainstMatches(Team team){
        return getMatches().stream().filter(match ->
            (match.getHomeTeam().equals(team) || match.getGuestTeam().equals(team))
        ).collect(Collectors.toList());
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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public int compareTo(Team o) {
        if (getPoints() == o.getPoints()){
            return 1;
        }
        else{
            return Integer.compare(getPoints(), o.getPoints());
        }
    }
}
