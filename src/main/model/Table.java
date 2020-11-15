package main.model;

import main.utils.TablePrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {

    //Member Variables
    private TableName tableName;
    private List<Team> teams;
    private List<Match> matches;

    //Constructor
    public Table(TableName tableName) {
        this.tableName = tableName;
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public Table(TableName tableName, List<Team> teams) {
        this.tableName = tableName;
        this.teams = teams;
    }

    //Methods
    public List<Team> getQualificationTeams(){

        List<Team> qualificationTeams = new ArrayList<>(2);

        Collections.sort(teams);

        qualificationTeams.add(teams.get(3));
        qualificationTeams.add(teams.get(2));

        return qualificationTeams;
    }

    //Getters and setters
    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void printTable(){

        System.out.println("\n"+"Table " + tableName);
        getTeams().sort(Collections.reverseOrder());
        String[][] table = new String[][] { { "Teams", "Played", "Won", "Drawn", "Lost",
                                                "For", "Against", "Goal Difference", "Points" },
                                                getTeams().get(0).getStringForReport(),
                                                getTeams().get(1).getStringForReport(),
                                                getTeams().get(2).getStringForReport(),
                                                getTeams().get(3).getStringForReport()};

        TablePrinter.tableWithLines(table);
    }
}
