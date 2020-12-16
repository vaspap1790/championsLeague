package main.model;

import main.model.Match;
import main.model.MatchDay;
import main.model.Table;
import main.model.Team;
import main.utils.DataInitializer;

import java.util.List;

public abstract class Round {

    //Member Variables
    private List<Table> tables;

    private List<MatchDay> matchDays;

    private List<Match> quarterFinals;

    private List<Match> semiFinals;

    private Match finalMatch;
    private Team championTeam;

    protected DataInitializer dataInitializer = new DataInitializer();

    //Constructor
    public Round(List<Table> tables, List<MatchDay> matchDays,
                 List<Match> quarterFinals, List<Match> semiFinals,
                 Match finalMatch, Team championTeam) {
        this.tables = tables;
        this.matchDays = matchDays;
        this.quarterFinals = quarterFinals;
        this.semiFinals = semiFinals;
        this.finalMatch = finalMatch;
        this.championTeam = championTeam;
    }

    public Round() {
    }

    //Start Methods
    public abstract void mainScreen();

    public abstract void  navigationMenu();

    public void start(){
        mainScreen();
        navigationMenu();
    }

    //Mode Methods
    public abstract void  selectMode();

    public abstract void  runAuto();

    public abstract boolean  runManual();

    //Utility Methods
    public abstract boolean setDatesManually();

    public abstract boolean setMatchesDetailsManually();

    //Proceed Methods
    public abstract void setQualifiers();

    public abstract void proceedToNextRound();

    //Reporting Methods
    public abstract void overview();

    public abstract void  report();

    //Getters-Setters
    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<MatchDay> getMatchDays() {
        return matchDays;
    }

    public void setMatchDays(List<MatchDay> matchDays) {
        this.matchDays = matchDays;
    }

    public List<Match> getQuarterFinals() {
        return quarterFinals;
    }

    public void setQuarterFinals(List<Match> quarterFinals) {
        this.quarterFinals = quarterFinals;
    }

    public List<Match> getSemiFinals() {
        return semiFinals;
    }

    public void setSemiFinals(List<Match> semiFinals) {
        this.semiFinals = semiFinals;
    }

    public Match getFinalMatch() {
        return finalMatch;
    }

    public void setFinalMatch(Match finalMatch) {
        this.finalMatch = finalMatch;
    }

    public Team getChampionTeam() {
        return championTeam;
    }

    public void setChampionTeam(Team championTeam) {
        this.championTeam = championTeam;
    }
}
