package main.utils;

import main.KickOff;
import main.model.*;
import main.rounds.GroupStage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.resources.Globals.*;

public class TournamentUtils {

    //Member Variables
    protected static DataInitializer dataInitializer = new DataInitializer();

    //Methods
    public static void startTournament(){

        ASCIIArt.printLogo();

        GroupStage groupStage = new GroupStage(
                new ArrayList<>(TABLES), dataInitializer.getMatchDays(),
                new ArrayList<>(QUARTERFINALS_MATCHES), new ArrayList<>(SEMIFINALS_MATCHES),
                new Match(), new Team());

        groupStage.start();

    }

    public static void resetMatchDaysDates(int start, int end, List<MatchDay> matchDays){
        for (int i = start; i < end; i++) {
            matchDays.get(i).setDate(null);
        }
    }

    public static void setDatesAuto(int start, int end, List<MatchDay> matchDays) {
        for (int i = start; i < end; i++){
            matchDays.get(i).setDate(dataInitializer.getMatchDates().get(i));
        }
    }

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
                System.out.println(match1);
                System.out.println(match2);
                qualifiedTeam = runPenalties(match1.getHomeTeam(), match1.getGuestTeam());
            }
        }
        return qualifiedTeam;
    }

    public static Team runPenalties(Team team1, Team team2){

        Team qualifiedTeam = new Team();

        System.out.println("\n" + team1.getName() + " and " + team2.getName() +
                " need to solve their differences in Penalties!" + "\n");
        System.out.println("If you want to manually run penalties, type.........1");
        System.out.println("If you want penalties to run automatically, type....2");

        String input;
        Penalties penalties = new Penalties(team1, team2);
        boolean penaltiesPlayed = false;
        do {
            input = KickOff.scanner.nextLine();
            switch (input) {
                case "1":
                    penalties.runManually();
                    System.out.println(penalties);
                    qualifiedTeam = penalties.getWinningTeam();
                    penaltiesPlayed = true;
                    break;
                case "2":
                    penalties.runAuto();
                    System.out.println(penalties);
                    qualifiedTeam = penalties.getWinningTeam();
                    penaltiesPlayed = true;
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        }while(!penaltiesPlayed);

        return qualifiedTeam;
    }

    public static List<Match> cloneList(List<Match> list) throws CloneNotSupportedException {
        List<Match> clone = new ArrayList<>(list.size());
        for (Match item : list) clone.add((Match) item.clone());
        return clone;
    }


    //User Input
    public static void enterMatchDayDate(int matchDayCounter, List<LocalDate> dates, List<MatchDay> matchDays){

        String date;
        String result;

        do {
            System.out.println("\n" + "Enter Date for the Match Day " + matchDayCounter + " ***Format YYYY-MM-DD***");
            date = KickOff.scanner.nextLine();
            result = Validator.dateCheck(date,dates,matchDayCounter-1);

            if(!result.equals(VALID)){
                System.out.println(result + ". Try again.");
            }
        } while (!result.equals(VALID));

        matchDays.get(matchDayCounter-1).setDate(LocalDate.parse(date));
    }

    public static boolean enterTeamsManually(List<Table> tables){

        enterTables(tables);
        String input;
        String result;

        for(Table table : tables){
            int counter = 1;
            do {

                System.out.println("Please enter Team " + counter + " for the " + table.getTableName() +
                        " Table or type 'back' to cancel (data will be lost)");
                input = KickOff.scanner.nextLine();
                result = Validator.emptyStringCheck(input);

                if(!"back".equals(input) && result.equals(VALID)){
                    table.getTeams().add(new Team(input));
                    counter++;
                }

                if(!result.equals(VALID)){
                    System.out.println(result);
                }

            }while(!"back".equals(input) && counter < 5);

            if("back".equals(input)){
                tables.forEach(objectTable -> objectTable.getTeams().clear());
                return false;
            }
        }
        return true;
    }

    public static HashMap<String,Integer> enterScore(String message1, String message2){

        HashMap<String,Integer> scores = new HashMap<>();
        String input;
        String result;

        //Goals Home Team
        do {
            System.out.println(message1);
            input = KickOff.scanner.nextLine();
            result = Validator.intCheck(input, 0, 10);
            if (!result.equals(VALID)) System.out.println(result + ". Try again");
        } while (!result.equals(VALID));
        int goalsHomeTeam = Integer.parseInt(input);

        //Goals Guest Team
        do {
            System.out.println(message2);
            input = KickOff.scanner.nextLine();
            result = Validator.intCheck(input, 0, 10);
            if (!result.equals(VALID)) System.out.println(result + ". Try again");
        } while (!result.equals(VALID));
        int goalsGuestTeam = Integer.parseInt(input);

        scores.put("goalsHomeTeam",goalsHomeTeam);
        scores.put("goalsGuestTeam",goalsGuestTeam);

        return scores;
    }

    //Initializers
    public static void enterTables(List<Table> tables){
        for (TableName tableName : TableName.values()){
            tables.add(new Table(tableName));
        }
    }

    public static void enterTeamsFromDummyData(List<Table> tables){

        enterTables(tables);

        for (int i = 0 ; i < 4 ; i++){
            tables.get(i).getTeams().add(new Team(DataInitializer.getRandomTeamsGroupA().get(i)));
            tables.get(i).getTeams().add(new Team(DataInitializer.getRandomTeamsGroupB().get(i)));
            tables.get(i).getTeams().add(new Team(DataInitializer.getRandomTeamsGroupC().get(i)));
            tables.get(i).getTeams().add(new Team(DataInitializer.getRandomTeamsGroupD().get(i)));
        }

    }

    public static ArrayList<Integer> arrayListIntegerInitializer(int size){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }

    public static void initializeGroupStageMatches(List<Table> tables, List<MatchDay> matchDays){
        for(Table table : tables){
            for(int i = 0 ; i < MATCHDAYS_GROUP_STAGE; i++){
                table.getMatches().add(new Match(matchDays.get(i)));
                table.getMatches().add(new Match(matchDays.get(i)));
            }
        }
    }

    public static void initializeQuarterFinalsMatches(List<Match> matches){
        for (int i = 0; i < QUARTERFINALS_MATCHES; i++) {
            matches.add(new Match());
        }
    }

    public static void initializeSemiFinalsMatches(List<Match> matches){
        for (int i = 0; i < SEMIFINALS_MATCHES; i++) {
            matches.add(new Match());
        }
    }

}
