package main.utils;

import main.KickOff;
import main.model.*;
import main.rounds.GroupStage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static main.Globals.*;

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

    public static void setDatesAuto(int start, int end, List<MatchDay> matchDays) {
        for (int i = start; i < end; i++){
            matchDays.get(i).setDate(dataInitializer.getMatchDates().get(i));
        }
    }

    public static Team findQualifiedTeam(Match match1, Match match2){

        Team qualifiedTeam;
        int team1Goals = match1.getHomeTeam().getGoalsFor() + match2.getGuestTeam().getGoalsFor();
        int team2Goals = match1.getGuestTeam().getGoalsFor() + match2.getHomeTeam().getGoalsFor();

        if(team1Goals > team2Goals){
            qualifiedTeam = match1.getHomeTeam();
        }
        else if (team1Goals < team2Goals){
            qualifiedTeam = match2.getHomeTeam();
        }
        else{

            int team1GoalsAway = match2.getGuestTeam().getGoalsFor();
            int team2GoalsAway = match2.getHomeTeam().getGoalsFor();

            if(team1GoalsAway > team2GoalsAway){
                qualifiedTeam = match1.getHomeTeam();
            }
            else if (team1GoalsAway < team2GoalsAway){
                qualifiedTeam = match2.getHomeTeam();
            }
            else{
                System.out.println(match1);
                System.out.println(match2);
                qualifiedTeam = runPenalties(match1.getHomeTeam(),match1.getGuestTeam());
            }
        }
        return qualifiedTeam;
    }

    public static Team runPenalties(Team team1, Team team2){

        Team qualifiedTeam = new Team();

        System.out.println(team1.getName() + " and " + team2.getName() +
                " need to solve their differences in Penalties!");
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

    //User Input
    public static void enterMatchInfo(int counter, List<Match> matches){

        String input;

        //Goals Home Team
        do {
            System.out.println("\n" + "Enter score for "+ matches.get(counter).getHomeTeam() + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, 0, 7).equals(VALID));
        int goalsH = Integer.parseInt(input);

        //Goals Guest Team
        do {
            System.out.println("\n" + "Enter score for " + matches.get(counter).getGuestTeam() + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, 0, 7).equals(VALID));
        int goalsG = Integer.parseInt(input);

        //Run match1
        matches.get(counter).runMatch(goalsH,goalsG);
    }

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

    //Initializes
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

    public static void initializeQuarterFinalsMatches(List<Match> matches, List<MatchDay> matchDays){

        int end = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS;

        for (int i = MATCHDAYS_GROUP_STAGE; i < end; i++) {
            matches.add(new Match(matchDays.get(i)));
            matches.add(new Match(matchDays.get(i)));
        }
    }

    public static void initializeSemiFinalsMatches(List<Match> matches, List<MatchDay> matchDays){

        int start = MATCHDAYS_GROUP_STAGE + QUARTERFINALS_MATCHES;
        int end = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS;

        for (int i = start; i < end; i++) {
            matches.add(new Match(matchDays.get(i)));
        }
    }

}
