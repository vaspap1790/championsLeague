package main.utils;

import main.KickOff;
import main.model.*;

import java.time.LocalDate;
import java.util.*;

import static main.Globals.*;

public class DataInitializer {

    //Member Variables
    private List<Table> tables = new ArrayList<>(TABLES);

    private List<MatchDay> matchDays = Arrays.asList(MatchDay.MatchDay1,MatchDay.MatchDay2,MatchDay.MatchDay3,
            MatchDay.MatchDay4,MatchDay.MatchDay5,MatchDay.MatchDay6, MatchDay.QuarterFinals1, MatchDay.QuarterFinals2,
            MatchDay.QuarterFinals3, MatchDay.QuarterFinals4, MatchDay.SemiFinals1, MatchDay.SemiFinals2, MatchDay.SemiFinals3,
            MatchDay.SemiFinals4, MatchDay.Finals);

    private List<Team> quarterFinalsTeams = new ArrayList<>(QUARTERFINALS_TEAMS_SIZE);
    private List<Match> quarterFinals = new ArrayList<>();

    private List<Team> semiFinalsTeams = new ArrayList<>(SEMIFINALS_TEAMS_SIZE);
    private List<Match> semiFinals = new ArrayList<>();

    private List<Team> finalTeams = new ArrayList<>(FINAL_TEAMS_SIZE);
    private Match finalMatch = new Match();
    private Team championTeam = new Team();


    //DummyData for Auto mode
    private List<String> randomTeamsGroupA = Arrays.asList("FC Barcelona", "Liverpool FC", "Real Madrid CF", "FC Bayern Munchen");

    private List<String> randomTeamsGroupB = Arrays.asList("Club Atletico de Madrid", "FC Internationale Milano", "Juventus", "Manchester United FC");

    private List<String> randomTeamsGroupC = Arrays.asList("RB Leipzig", "Olympiacos FC", "FC Shakhtar Donetsk", "FC Salzburg");

    private List<String> randomTeamsGroupD = Arrays.asList( "Ferencvarosi TC", "Club Brugge", "Stade Rennais FC", "FC Midtjylland");

    private List<LocalDate> matchDates = Arrays.asList(
            LocalDate.of(2020,10,20), LocalDate.of(2020,10,27),
            LocalDate.of(2020,11,3), LocalDate.of(2020,11,24),
            LocalDate.of(2020,12,1), LocalDate.of(2020,12,8),
            LocalDate.of(2021,3,16),LocalDate.of(2021,3,17),
            LocalDate.of(2021,3,23),LocalDate.of(2021,3,24),
            LocalDate.of(2021,4,13),LocalDate.of(2021,4,14),
            LocalDate.of(2021,4,20),LocalDate.of(2021,4,21),
            LocalDate.of(2021,5,5));


    //Methods
    public void enterTables(){
        for (TableName tableName : TableName.values()){
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
                System.out.println("- Please enter Team " + counter + " for the " + table.getTableName() + " Table or type 'back' to cancel. -");
                input = KickOff.scanner.nextLine();
                table.getTeams().add(new Team(input));
                counter++;
            }while(!"back".equals(input) && counter < 5);

            if("back".equals(input)){
                break;
            }
        }
    }

    public void enterTeamsFromDummyData(){

        enterTables();

        for (int i = 0 ; i < 4 ; i++){
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

    public List<MatchDay> getMatchDays() {
        return matchDays;
    }

    public void setMatchDays(List<MatchDay> matchDays) {
        this.matchDays = matchDays;
    }

    public List<Team> getQuarterFinalsTeams() {
        return quarterFinalsTeams;
    }

    public void setQuarterFinalsTeams(List<Team> quarterFinalsTeams) {
        this.quarterFinalsTeams = quarterFinalsTeams;
    }

    public List<LocalDate> getMatchDates() {
        return matchDates;
    }

    public void setMatchDates(List<LocalDate> matchDates) {
        this.matchDates = matchDates;
    }

    public List<Match> getQuarterFinals() {
        return quarterFinals;
    }

    public void setQuarterFinals(List<Match> quarterFinals) {
        this.quarterFinals = quarterFinals;
    }

    public List<Team> getSemiFinalsTeams() {
        return semiFinalsTeams;
    }

    public void setSemiFinalsTeams(List<Team> semiFinalsTeams) {
        this.semiFinalsTeams = semiFinalsTeams;
    }

    public List<Match> getSemiFinals() {
        return semiFinals;
    }

    public void setSemiFinals(List<Match> semiFinals) {
        this.semiFinals = semiFinals;
    }

    public List<Team> getFinalTeams() {
        return finalTeams;
    }

    public void setFinalTeams(List<Team> finalTeams) {
        this.finalTeams = finalTeams;
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
