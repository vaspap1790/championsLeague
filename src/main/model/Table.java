package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Table ").append(tableName);
        sb.append(System.getProperty("line.separator"));

        getTeams().forEach(team -> sb.append(team.toString()));

        return sb.toString();
    }
}
