package com.athtech.utils;

import com.athtech.model.Table;
import com.athtech.model.TableName;
import com.athtech.model.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataInitializer {

    private List<String> randomTeamsGroupA = Arrays.asList("FC Barcelona", "Borussia Dortmund", "Chealsea FC",
            "Liverpool FC", "Manchester City FC", "Real Madrid CF", "FC Bayern Munchen", "Paris Saint-Germain");

    private List<String> randomTeamsGroupB = Arrays.asList("Club Atletico de Madrid", "FC Internationale Milano", "FC Porto",
            "AFC Ajax", "Sevilla FC", "SS Lazio", "Juventus", "Manchester United FC");

    private List<String> randomTeamsGroupC = Arrays.asList("RB Leipzig", "FC Dynamo Kyiv", "FC Zenit",
            "FC Krasnodar", "Atalanta BC", "Olympiacos FC", "FC Shakhtar Donetsk", "FC Salzburg");

    private List<String> randomTeamsGroupD = Arrays.asList("Instanbul Basaksehir", "Ferencvarosi TC", "Club Brugge",
            "Stade Rennais FC", "FC Midtjylland", "Olympique de Marseille", "Vfl Borussia Monchengladbach", "FC Lolomotiv Moskva");
    
    private List<Table> tables = new ArrayList<>(8);

    public void enterTables(){
        for (TableName tableName : TableName.values()){
            tables.add(new Table(tableName));
        }
    }

    public void enterTeamsManually(){

    }

    public void enterTeamsFromDummyData(){

        enterTables();

        for (int i = 0 ; i < 8 ; i++){
            tables.get(i).getTeams().add(new Team(randomTeamsGroupA.get(i)));
            tables.get(i).getTeams().add(new Team(randomTeamsGroupB.get(i)));
            tables.get(i).getTeams().add(new Team(randomTeamsGroupC.get(i)));
            tables.get(i).getTeams().add(new Team(randomTeamsGroupD.get(i)));
        }

    }

    //Getters-Setters
    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
