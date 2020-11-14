package com.athtech.model;

import java.util.ArrayList;
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
}
