package test.utils;

import main.model.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TournamentUtilsStub {

    //Methods
    public static Team findQualifiedTeam(Match match1, Match match2){

        Team qualifiedTeam;
        int team1Goals = match1.getGoalsForHTeam() + match2.getGoalsForGTeam();
        int team2Goals = match1.getGoalsForGTeam() + match2.getGoalsForHTeam();

        if(team1Goals > team2Goals){
            qualifiedTeam = match1.getHomeTeam();
        }
        else if (team1Goals < team2Goals){
            qualifiedTeam = match2.getHomeTeam();
        }
        else{

            int team1GoalsAway = match2.getGoalsForGTeam();
            int team2GoalsAway = match1.getGoalsForGTeam();

            if(team1GoalsAway > team2GoalsAway){
                qualifiedTeam = match1.getHomeTeam();
            }
            else if (team1GoalsAway < team2GoalsAway){
                qualifiedTeam = match2.getHomeTeam();
            }
            else{
                qualifiedTeam = new Team("Run Penalties");
            }
        }
        return qualifiedTeam;
    }

    public static Team runPenalties(String data){

        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Team qualifiedTeam;

            switch (input) {
                case "1":
                    qualifiedTeam = new Team("Run Penalties manually");
                    break;
                case "2":
                    qualifiedTeam = new Team("Run Penalties auto");
                    break;
                default:
                    qualifiedTeam = new Team("Invalid input. Penalties not played");
                    break;
            }

        return qualifiedTeam;
    }

    //User Input
    public static void enterMatchDayDate(int matchDayCounter, List<MatchDay> matchDays){

        //The validity of the input date is checked within validator tests,
        //so here we hard code it
        String date = "2020-10-10";

        matchDays.get(matchDayCounter-1).setDate(LocalDate.parse(date));
    }

    public static boolean enterTeamsManually(List<Table> tables, String data){

        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //TournamentUtilsImpl.enterTables() will be tested independently
        for (TableName tableName : TableName.values()){
            tables.add(new Table(tableName));
        }

        //The validity of the input is checked within ValidatorTest
        for(Table table : tables){
            int counter = 1;
            do {
                table.getTeams().add(new Team());
                counter++;
            }while(counter < 5);

            if("back".equals(input)){
                tables.forEach(objectTable -> objectTable.getTeams().clear());
                return false;
            }
        }
        return true;
    }

    public static HashMap<String,Integer> enterScore(String message1, String message2){

        HashMap<String,Integer> scores = new HashMap<>();

        //The validity of the input is checked within ValidatorTest
        System.setIn(new ByteArrayInputStream(message1.getBytes()));
        Scanner scanner1 = new Scanner(System.in);
        String input1 = scanner1.nextLine();
        int goalsHomeTeam = Integer.parseInt(input1);

        System.setIn(new ByteArrayInputStream(message2.getBytes()));
        Scanner scanner2 = new Scanner(System.in);
        String input2 = scanner2.nextLine();
        int goalsGuestTeam = Integer.parseInt(input2);

        scores.put("goalsHomeTeam",goalsHomeTeam);
        scores.put("goalsGuestTeam",goalsGuestTeam);

        return scores;
    }

}
