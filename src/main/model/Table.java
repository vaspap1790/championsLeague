package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {

    //Member Variables
    private TableName tableName;
    private List<Team> teams;

    //Constructor
    public Table(TableName tableName) {
        this.tableName = tableName;
        this.teams = new ArrayList<>();
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

}
