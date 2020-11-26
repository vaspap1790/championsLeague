package main.utils;

import main.KickOff;
import main.model.*;
import main.rounds.GroupStage;

import java.time.LocalDate;
import java.util.List;

import static main.Globals.VALID;

public class TournamentUtils {

    public static void startTournament(){

        ASCIIArt.printLogo();

        GroupStage groupStage = new GroupStage();
        groupStage.start();

    }

    public static void setDatesAuto(int start, int end, List<MatchDay> matchDays, List<LocalDate> dates) {
        for (int i = start; i < end; i++){
            matchDays.get(i).setDate(dates.get(i));
        }
    }

    public static Team findQualifiedTeam(Match match1, Match match2){

        Team qualifiedTeam = new Team();
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
                runPenalties(match1.getHomeTeam(),match1.getGuestTeam(),qualifiedTeam);
            }
        }
        return qualifiedTeam;
    }

    public static void runPenalties(Team team1, Team team2, Team qualifiedTeam){

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
    }

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

    public static void enterMatchDayDate(int matchDayCounter, List<MatchDay> matchDays){
        String date;
        boolean correctFormat;

        do {
            System.out.println("\n" + "Enter Date for the " + matchDayCounter +" Match Day. ***Format YYYY-MM-DD***" + "\n");
            date = KickOff.scanner.nextLine();
            correctFormat = Validator.dateCheck(date);
        } while (!correctFormat);

        matchDays.get(matchDayCounter).setDate(LocalDate.parse(date));
    }

    public static void enterTables(List<Table> tables){
        for (TableName tableName : TableName.values()){
            tables.add(new Table(tableName));
        }
    }

    public static void enterTeamsManually(List<Table> tables){

        enterTables(tables);
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

    public static void enterTeamsFromDummyData(List<Table> tables){

        enterTables(tables);

        for (int i = 0 ; i < 4 ; i++){
            tables.get(i).getTeams().add(new Team(DataInitializer.getRandomTeamsGroupA().get(i)));
            tables.get(i).getTeams().add(new Team(DataInitializer.getRandomTeamsGroupB().get(i)));
            tables.get(i).getTeams().add(new Team(DataInitializer.getRandomTeamsGroupC().get(i)));
            tables.get(i).getTeams().add(new Team(DataInitializer.getRandomTeamsGroupD().get(i)));
        }

    }

}
