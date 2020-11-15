package main.utils;

import main.KickOff;
import main.model.MatchDay;
import main.model.Table;
import main.model.TableName;
import main.model.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataInitializer {

    //Member Variables
    private List<Table> tables = new ArrayList<>(8);

    //DummyData for Auto mode
    private List<String> randomTeamsGroupA = Arrays.asList("FC Barcelona", "Borussia Dortmund", "Chealsea FC",
            "Liverpool FC", "Manchester City FC", "Real Madrid CF", "FC Bayern Munchen", "Paris Saint-Germain");

    private List<String> randomTeamsGroupB = Arrays.asList("Club Atletico de Madrid", "FC Internationale Milano", "FC Porto",
            "AFC Ajax", "Sevilla FC", "SS Lazio", "Juventus", "Manchester United FC");

    private List<String> randomTeamsGroupC = Arrays.asList("RB Leipzig", "FC Dynamo Kyiv", "FC Zenit",
            "FC Krasnodar", "Atalanta BC", "Olympiacos FC", "FC Shakhtar Donetsk", "FC Salzburg");

    private List<String> randomTeamsGroupD = Arrays.asList("Instanbul Basaksehir", "Ferencvarosi TC", "Club Brugge",
            "Stade Rennais FC", "FC Midtjylland", "Olympique de Marseille", "VfL Borussia Monchengladbach", "FC Lolomotiv Moskva");

    private List<LocalDate> matchDates = Arrays.asList(LocalDate.of(2020,10,20),
            LocalDate.of(2020,10,27),LocalDate.of(2020,11,3),
            LocalDate.of(2020,11,24),LocalDate.of(2020,12,1),
            LocalDate.of(2020,12,8),LocalDate.of(2021,3,16),
            LocalDate.of(2021,3,23),LocalDate.of(2021,4,13),
            LocalDate.of(2021,4,20),LocalDate.of(2021,5,5));


    //Methods
    public void enterTables(){
        for (TableName tableName : TableName.getTableNames()){
            tables.add(new Table(tableName));
        }
    }

    public void enterTeamsManually(){

        enterTables();
        String input;

        for(Table table : tables){
            int counter = 1;
            do {
                System.out.println("\n");
                System.out.println("- Please enter Team " + counter + " for the " + table.getTableName() + " Table or type '0' to cancel. -");
                input = KickOff.scanner.nextLine();
                table.getTeams().add(new Team(input));
                counter++;
            }while(!"0".equals(input) && counter < 5);

            if("0".equals(input)){
                break;
            }
        }
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

    public void setDatesGroupStage(List<MatchDay> matchDays){
        for (int i = 0; i < matchDays.size(); i++){
            matchDays.get(i).setDate(matchDates.get(i));
        }
    }

    public void setDatesQuarterFinals(List<MatchDay> matchDays){
        for (int i = 0; i < matchDays.size(); i++){
            matchDays.get(i).setDate(matchDates.get(i+6));
        }
    }

    public void setDatesSemiFinals(List<MatchDay> matchDays){
        for (int i = 0; i < matchDays.size(); i++){
            matchDays.get(i).setDate(matchDates.get(i+6));
        }
    }

    public void setDateFinal(MatchDay matchDay){
        matchDay.setDate(matchDates.get(11));
    }

    //Getters-Setters
    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
