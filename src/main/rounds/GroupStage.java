package main.rounds;

import main.KickOff;

import main.model.*;
import main.utils.ASCIIArt;
import main.utils.TournamentUtils;
import main.utils.Validator;

import java.util.*;

import static main.Globals.*;

public class GroupStage extends Round{

    ///////////////////////////////////////////////Start Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void mainScreen() {
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("*******************************************************************************************");
        System.out.println("**                                     Group Stage                                       **");
        System.out.println("*******************************************************************************************");
    }

    @Override
    public void navigationMenu() {

        String input;
        boolean proceed = false;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to enter the Teams manually, type.........................................'1'-");
            System.out.println("-If you want teams to be selected automatically, type..................................'2'-");
            System.out.println("-If you want to end the Tournament, type...............................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    TournamentUtils.enterTeamsManually(dataInitializer.getTables());
                    selectMode();
                    proceedToNextRound();
                    proceed = true;
                    break;
                case "2":
                    TournamentUtils.enterTeamsFromDummyData(dataInitializer.getTables());
                    selectMode();
                    proceedToNextRound();
                    proceed = true;
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input) && !proceed);

        if("0".equals(input)){
            ASCIIArt.end();
        }
    }

    ////////////////////////////////////////////////////Mode Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void selectMode(){
        String input;
        boolean modeSelected = false;
        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to enter all scores for all matches of Group Stage manually, type.........'1'-");
            System.out.println("-If you want Group Stage to run automatically, type....................................'2'-");
            System.out.println("-If you want to go back, type..........................................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    runManual();
                    modeSelected = true;
                    break;
                case "2":
                    runAuto();
                    modeSelected = true;
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input) && !modeSelected);
    }

    @Override
    public void runAuto() {

        TournamentUtils.setDatesAuto(0,MATCHDAYS_GROUP_STAGE,dataInitializer.getMatchDays(),dataInitializer.getMatchDates());

        for (Table table : dataInitializer.getTables()) {

            Random random = new Random();

            //Run MatchDay1
            Match match1 = new Match(table.getTeams().get(0),table.getTeams().get(3),dataInitializer.getMatchDays().get(0));
            match1.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match1);

            Match match2 = new Match(table.getTeams().get(1),table.getTeams().get(2),dataInitializer.getMatchDays().get(0));
            match2.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match2);

            //Run MatchDay2
            Match match3 = new Match(table.getTeams().get(0),table.getTeams().get(1),dataInitializer.getMatchDays().get(1));
            match3.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match3);

            Match match4 = new Match(table.getTeams().get(2),table.getTeams().get(3),dataInitializer.getMatchDays().get(1));
            match4.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match4);

            //Run MatchDay3
            Match match5 = new Match(table.getTeams().get(0),table.getTeams().get(2),dataInitializer.getMatchDays().get(2));
            match5.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match5);

            Match match6 = new Match(table.getTeams().get(1),table.getTeams().get(3),dataInitializer.getMatchDays().get(2));
            match6.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match6);

            //Run MatchDay4
            Match match7 = new Match(table.getTeams().get(3),table.getTeams().get(0),dataInitializer.getMatchDays().get(3));
            match7.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match7);

            Match match8 = new Match(table.getTeams().get(2),table.getTeams().get(1),dataInitializer.getMatchDays().get(3));
            match8.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match8);

            //Run MatchDay5
            Match match9 = new Match(table.getTeams().get(1),table.getTeams().get(0),dataInitializer.getMatchDays().get(4));
            match9.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match9);

            Match match10 = new Match(table.getTeams().get(3),table.getTeams().get(2),dataInitializer.getMatchDays().get(4));
            match10.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match10);

            //Run MatchDay6
            Match match11 = new Match(table.getTeams().get(2),table.getTeams().get(0),dataInitializer.getMatchDays().get(5));
            match11.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match11);

            Match match12 = new Match(table.getTeams().get(3),table.getTeams().get(1),dataInitializer.getMatchDays().get(5));
            match12.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match12);

        }
    }

    @Override
    public void runManual() {
        initializeMatches();

        System.out.println("There are 6 Match Days to be arranged in every Table.");
        System.out.println("You should enter data for two matches in every Match Day.");

        setDatesManually();
        setMatchesDetailsManually();
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setDatesManually(){

        System.out.println("Firstly, you have to set dates for the six Match Days of the Group Stage.");
        String input;

        for(Table table : dataInitializer.getTables()) {

            int counter = 1;
            table.printTableForSelection();

            do {
                System.out.println("\n");
                System.out.println("To enter the dates type '1', or type 'back' to cancel (data will be lost).");
                input = KickOff.scanner.nextLine();

                switch (input) {
                    case "back":
                        break;
                    case "1":
                        System.out.println("\n");
                        System.out.println("Match Day " + counter);
                        System.out.println("\n");
                        TournamentUtils.enterMatchDayDate(counter,dataInitializer.getMatchDays());
                        break;
                    default:
                        System.out.println("\n" + "Invalid input. Try again." + "\n");
                        break;
                }
                counter++;

            } while (!"back".equals(input) && counter <= MATCHDAYS_GROUP_STAGE);

            if ("back".equals(input)) {
                break;
            }
        }
    }

    @Override
    public void setMatchesDetailsManually(){

        System.out.println("Now, you have to set the match details for the 12 matches of the four tables.");
        String input;

        for(Table table : dataInitializer.getTables()){

            int counter = 1;

            do {
                System.out.println("\n");
                System.out.println("To enter match data type '1', or type 'back' to cancel (data will be lost)");
                input = KickOff.scanner.nextLine();

                switch (input) {
                    case "back":
                        break;
                    case "1":
                        System.out.println("\n");
                        System.out.println("Match Day " + counter + " Table " + table.getTableName());
                        System.out.println("\n");
                        enterMatchInfoPhase1(counter, table);
                        enterMatchInfoPhase2(counter + 3, table);
                        enterMatchInfoPhase1(counter + 1, table);
                        enterMatchInfoPhase2(counter + 4, table);
                        System.out.println();
                        break;
                    default:
                        System.out.println("\n" + "Invalid input. Try again." + "\n");
                        break;
                }
                counter++;

            }while(!"back".equals(input) && counter <= MATCHDAYS_GROUP_STAGE / 2);

            if("back".equals(input)){
                break;
            }
        }
    }

    ////////////////////////////////////Round Specific Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void enterMatchInfoPhase1(int matchDayCounter, Table table){

        String input;
        List<Integer> positions = Arrays.asList(1,2,3,4);

        //Home Team
        do {
            System.out.println("\n" + "Enter Home Team for the 1st Match of the " + matchDayCounter + " Match Day of the "+ table.getTableName() +" Table, Phase 1." + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, positions).equals(VALID));

        int indexH1 = Integer.parseInt(input);
        table.getMatches().get(matchDayCounter).setHomeTeam(table.getTeams().get(indexH1 - 1));
        table.getMatches().get(matchDayCounter + 3).setGuestTeam(table.getTeams().get(indexH1 - 1));
        positions.remove(indexH1 - 1);

        //Guest Team
        do {
            System.out.println("\n" + "Enter Home Team for the 1st Match of the " + matchDayCounter + " Match Day of the "+ table.getTableName() +" Table, Phase 1." + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, positions).equals(VALID));

        int indexG1 = Integer.parseInt(input);
        table.getMatches().get(matchDayCounter).setGuestTeam(table.getTeams().get(indexG1 - 1));
        table.getMatches().get(matchDayCounter + 3).setHomeTeam(table.getTeams().get(indexG1 - 1));
        positions.remove(indexG1 - 1);

        //Goals Home Team
        do {
            System.out.println("\n" + "Enter score for "+ table.getMatches().get(matchDayCounter).getHomeTeam()
                    + " for the 1st Match of the " + matchDayCounter + " Match Day of the "+ table.getTableName() +" Table, Phase 1." + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, 0, 7).equals(VALID));
        int goalsH = Integer.parseInt(input);

        //Goals Guest Team
        do {
            System.out.println("\n" + "Enter score for " + table.getMatches().get(matchDayCounter).getGuestTeam()
                    + " for the 1st Match of the " + matchDayCounter + " Match Day of the "+ table.getTableName() +" Table, Phase 1." + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, 0, 7).equals(VALID));
        int goalsG = Integer.parseInt(input);

        //Run match1
        table.getMatches().get(matchDayCounter).runMatch(goalsH,goalsG);

    }

    public void enterMatchInfoPhase2(int matchDayCounter, Table table){

        String input;
        List<Integer> positions = Arrays.asList(1,2,3,4);

        //Goals Home Team
        do {
            System.out.println("\n" + "Enter score for "+ table.getMatches().get(matchDayCounter).getHomeTeam()
                    + " for the 2nd Match of the " + matchDayCounter + " Match Day of the "+ table.getTableName() +" Table, Phase 2." + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, 0, 7).equals(VALID));
        int goalsH = Integer.parseInt(input);

        //Goals Guest Team
        do {
            System.out.println("\n" + "Enter score for " + table.getMatches().get(matchDayCounter).getGuestTeam()
                    + " for the 2nd Match of the " + matchDayCounter + " Match Day of the "+ table.getTableName() +" Table, Phase 2." + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, 0, 7).equals(VALID));
        int goalsG = Integer.parseInt(input);

        //Run match1
        table.getMatches().get(matchDayCounter + 1).runMatch(goalsH,goalsG);

    }

    public void initializeMatches(){
        for(Table table : dataInitializer.getTables()){
            for(int i = 0 ; i< dataInitializer.getMatchDays().size(); i++){
                table.getMatches().add(new Match(dataInitializer.getMatchDays().get(i)));
                table.getMatches().add(new Match(dataInitializer.getMatchDays().get(i)));
            }
        }
    }

    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers(){

        for (int i = 0 ; i < TABLES ; i++){

            dataInitializer.getTables().get(i).getTeams().sort(Collections.reverseOrder());

            Team team1 = dataInitializer.getTables().get(i).getTeams().get(0);
            Team team2 = dataInitializer.getTables().get(i).getTeams().get(1);

            team1.setQualificationCode(new QualificationCode(dataInitializer.getTables().get(i).getTableName(),1));
            team2.setQualificationCode(new QualificationCode(dataInitializer.getTables().get(i).getTableName(),2));

            dataInitializer.getQuarterFinalsTeams().set(i, team1);
            dataInitializer.getQuarterFinalsTeams().set(i+4 ,team2);

        }
    }

    @Override
    public void proceedToNextRound() {
        String input;
        boolean proceed = false;
        QuarterFinals quarterFinals = new QuarterFinals();

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to proceed to Quarter Finals, type........................................'1'-");
            System.out.println("-If you want to see the matches of Group Stage, type...................................'2'-");
            System.out.println("-If you want to see the standings of Group Stage, type.................................'3'-");
            System.out.println("-If you want to end the Tournament, type...............................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    setQualifiers();
                    quarterFinals.start();
                    proceed = true;
                    break;
                case "2":
                    report();
                    break;
                case "3":
                    overview();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input) && !proceed);

        if("0".equals(input)){
            ASCIIArt.end();
        }
    }

    ////////////////////////////////////////////Reporting Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void overview() {
        dataInitializer.getTables().forEach(Table::printTable);
    }

    @Override
    public void report() {

        System.out.println("*******************GROUP STAGE*********************" + "\n");

        System.out.println("*********************PHASE 1***********************" + "\n");

        for(int i = 0; i < MATCHDAYS_GROUP_STAGE /2 ; i++){
            System.out.println("---------------" + dataInitializer.getMatchDays().get(i).toString() + " "
                    + dataInitializer.getMatchDays().get(i).getDate() + "---------------" + "\n");
            for (Table table :  dataInitializer.getTables()){
                System.out.println("Table " + table.getTableName());
                for(Match match : table.getMatches()){
                    if(match.getMatchDay() == dataInitializer.getMatchDays().get(i)){
                        System.out.println(match);
                    }
                }
                System.out.println();
            }
        }

        System.out.println("*********************PHASE 2***********************" + "\n");

        for(int i = (MATCHDAYS_GROUP_STAGE /2); i < MATCHDAYS_GROUP_STAGE; i++){
            System.out.println("---------------" + dataInitializer.getMatchDays().get(i).toString() + " "
                    + dataInitializer.getMatchDays().get(i).getDate() + "---------------" + "\n");
            for (Table table :  dataInitializer.getTables()){
                System.out.println("Table " + table.getTableName());
                for(Match match : table.getMatches()){
                    if(match.getMatchDay() == dataInitializer.getMatchDays().get(i)){
                        System.out.println(match);
                    }
                }
                System.out.println();
            }
        }
    }

}
