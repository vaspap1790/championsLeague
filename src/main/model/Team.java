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
    private List<Match> matches;

    //Constructors
    public Team(String name) {
        this.name = name;
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
                oppositeTeamGoals += match.getGoalsForHTeam();
                teamGoals += match.getGoalsForGTeam();
            }else{
                oppositeTeamGoals += match.getGoalsForGTeam();
                teamGoals += match.getGoalsForHTeam();
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
    
    public String[] getStringForReport(){
      return new String[]{name, String.valueOf(gamesPlayed), String.valueOf(gamesWon), String.valueOf(gamesDrawn),
              String.valueOf(gamesLost), String.valueOf(goalsFor), String.valueOf(goalsAgainst),
              String.valueOf(getGoalDifference()), String.valueOf(getPoints())};
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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    //Overridden from Object
    @Override
    public int compareTo(Team o) {
        if (getPoints() == o.getPoints()){
            return resolveEqualRanking(o);
        }
        else{
            return Integer.compare(getPoints(), o.getPoints());
        }
    }
}
