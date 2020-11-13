package com.athtech.model;

import java.util.List;

public class Table {

    //Member Variables
    private TableName tableName;
    private List<Team> teams;
    private List<Match> matches;

    //Constructor
    public Table(TableName tableName) {
        this.tableName = tableName;
    }
}
