package main.model;

import main.KickOff;

import main.utils.ASCIIArt;
import main.utils.TournamentUtils;
import main.utils.Validator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static main.resources.Globals.*;

public class GroupStage extends Round{
    
    //Constructors
    public GroupStage(List<Table> tables, List<MatchDay> matchDays,
                      List<Match> quarterFinals, List<Match> semiFinals,
                      Match finalMatch, Team championTeam) {
        super(tables, matchDays, quarterFinals, semiFinals, finalMatch, championTeam);
    }

    public GroupStage(List<Table> tables, List<MatchDay> matchDays) {
        super.setTables(tables);
        super.setMatchDays(matchDays);
    }

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
                    proceed = TournamentUtils.enterTeamsManually(getTables());
                    if(proceed){
                        ASCIIArt.success();
                        System.out.println("\n" + "You entered the Teams!");
                    }else{
                        ASCIIArt.fail();
                        System.out.println("\n" + "You canceled entering the Teams manually");
                    }
                    ASCIIArt.next();
                    break;
                case "2":
                    TournamentUtils.enterTeamsFromDummyData(getTables());
                    proceed = true;
                    ASCIIArt.success();
                    System.out.println("\n" + "Teams were selected automatically!");
                    ASCIIArt.next();
                    break;
                default:
                    ASCIIArt.fail();
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input) && !proceed);

        if(proceed){
            selectMode();
        }

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
            System.out.println("-If you want to enter all dates and scores for all matches of Group Stage, type.........1'-");
            System.out.println("-If you want Group Stage to run automatically, type....................................'2'-");
            System.out.println("-If you want to end the Tournament, type...............................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    modeSelected = runManual();
                    if(modeSelected){
                        ASCIIArt.success();
                        System.out.println("\n" + "You have run Group Stage manually!");
                    }else{
                        ASCIIArt.fail();
                        System.out.println("\n" + "You canceled running the Group Stage manually");
                    }
                    ASCIIArt.next();
                    break;
                case "2":
                    runAuto();
                    modeSelected = true;
                    ASCIIArt.success();
                    System.out.println("\n" + "Group Stage has run automatically");
                    ASCIIArt.next();
                    break;
                default:
                    ASCIIArt.fail();
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input) && !modeSelected);

        if(modeSelected){
            proceedToNextRound();
        }

        if("0".equals(input)){
            ASCIIArt.end();
        }
    }

    @Override
    public void runAuto() {

        TournamentUtils.setDatesAuto(0, MATCHDAYS_GROUP_STAGE, getMatchDays());

        for (Table table : getTables()) {

            Random random = new Random();

            //Run MatchDay1
            Match match1 = new Match(table.getTeams().get(0),table.getTeams().get(3),getMatchDays().get(0));
            match1.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match1);

            Match match2 = new Match(table.getTeams().get(1),table.getTeams().get(2),getMatchDays().get(0));
            match2.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match2);

            //Run MatchDay2
            Match match3 = new Match(table.getTeams().get(0),table.getTeams().get(1),getMatchDays().get(1));
            match3.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match3);

            Match match4 = new Match(table.getTeams().get(2),table.getTeams().get(3),getMatchDays().get(1));
            match4.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match4);

            //Run MatchDay3
            Match match5 = new Match(table.getTeams().get(0),table.getTeams().get(2),getMatchDays().get(2));
            match5.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match5);

            Match match6 = new Match(table.getTeams().get(1),table.getTeams().get(3),getMatchDays().get(2));
            match6.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match6);

            //Run MatchDay4
            Match match7 = new Match(table.getTeams().get(3),table.getTeams().get(0),getMatchDays().get(3));
            match7.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match7);

            Match match8 = new Match(table.getTeams().get(2),table.getTeams().get(1),getMatchDays().get(3));
            match8.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match8);

            //Run MatchDay5
            Match match9 = new Match(table.getTeams().get(1),table.getTeams().get(0),getMatchDays().get(4));
            match9.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match9);

            Match match10 = new Match(table.getTeams().get(3),table.getTeams().get(2),getMatchDays().get(4));
            match10.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match10);

            //Run MatchDay6
            Match match11 = new Match(table.getTeams().get(2),table.getTeams().get(0),getMatchDays().get(5));
            match11.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match11);

            Match match12 = new Match(table.getTeams().get(3),table.getTeams().get(1),getMatchDays().get(5));
            match12.runGroupStageMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match12);

        }
    }

    @Override
    public boolean runManual() {
        TournamentUtils.initializeGroupStageMatches(getTables(), getMatchDays());
        return setDatesManually();
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public boolean setDatesManually(){

        ASCIIArt.enter();
        System.out.println("\n" + "Firstly, you have to set dates for the six Match Days of the Group Stage.");
        String input;
        int matchDayCounter = 1;
        int matchDayIndex = matchDayCounter - 1;
        int matchDayIndexEnd = MATCHDAYS_GROUP_STAGE;
        List<LocalDate> dates;

        do {
            dates = getMatchDays().stream().map(MatchDay::getDate).collect(Collectors.toList());
            System.out.println("\n" + "To enter the date of MatchDay " + matchDayCounter
                    + " type '1', or type 'back' to cancel (data will be lost).");
            input = KickOff.scanner.nextLine();

            switch (input) {
                case "back":
                    break;
                case "1":
                    TournamentUtils.enterMatchDayDate(matchDayCounter, dates, getMatchDays());
                    matchDayCounter++;
                    break;
                default:
                    ASCIIArt.fail();
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }

        } while (!"back".equals(input) && matchDayCounter <= matchDayIndexEnd);

        if ("back".equals(input)) {
            TournamentUtils.resetMatchDaysDates(matchDayIndex, matchDayIndexEnd, getMatchDays());
            getTables().forEach(table1 -> table1.getMatches().clear());
            return false;
        }

        ASCIIArt.success();
        System.out.println("\n" + "You successfully arranged the MathDay Dates for the Group Stage!");

        if(setMatchesDetailsManually()){
            System.out.println("\n" + "You successfully arranged all matches for the Group Stage!");
            return true;
        }else{
            TournamentUtils.resetMatchDaysDates(matchDayIndex, matchDayIndexEnd, getMatchDays());
            getTables().forEach(table1 -> table1.getMatches().clear());
            return false;
        }
    }

    @Override
    public boolean setMatchesDetailsManually(){

        ASCIIArt.enter();
        System.out.println("\n" + "Now, you have to set the match details for the 12 matches of each table.");
        String input;

        for(Table table : getTables()){

            int matchDayCounter = 1;

            do {
                System.out.println("\n" + "To enter match data type '1', or type 'back' to cancel (data will be lost)");
                int phaseTwoMatchDayCounter = matchDayCounter + 3;
                input = KickOff.scanner.nextLine();

                switch (input) {
                    case "back":
                        break;
                    case "1":
                        table.printTableForSelection();
                        System.out.println();
                        System.out.println("\n" + "Match Day " + matchDayCounter + " Table " + table.getTableName());
                        enterMatchInfo(matchDayCounter, table);
                        System.out.println("\n" + "You successfully arranged Match Days " + matchDayCounter + "," + phaseTwoMatchDayCounter + " for Table " + table.getTableName());
                        matchDayCounter++;
                        break;
                    default:
                        ASCIIArt.fail();
                        System.out.println("\n" + "Invalid input. Try again." + "\n");
                        break;
                }

            }while(!"back".equals(input) && matchDayCounter <= MATCHDAYS_GROUP_STAGE / 2);

            if("back".equals(input)){
                return false;
            }
        }

        return true;
    }

    ////////////////////////////////////Round Specific Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void enterMatchInfo(int matchDayCounter, Table table){

        ArrayList<Integer> positions = TournamentUtils.arrayListIntegerInitializer(4);
        HashMap<String,Integer> teamsIndexes;

        int phaseTwoMatchDayCounter = matchDayCounter + 3;
        int matchDayArrayIndex = matchDayCounter - 1;
        int matchArrayIndex = 2 *  matchDayArrayIndex;
        int phaseTwoMatchArrayIndex = matchArrayIndex + 6;
        int matchCounter = 1;
        int phase = 1;

        ////////////////////////////////////Select Teams for the matches\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        teamsIndexes = selectDates(positions, table, matchCounter, matchDayCounter, phase);

        //Check if match between these two team has already been arranged
        if(matchDayCounter != 1){
            boolean matchAlreadyArranged = false;

            do{

                Match match1 = new Match(table.getTeams().get(teamsIndexes.get("indexHomeTeam") - 1), table.getTeams().get(teamsIndexes.get("indexGuestTeam") - 1));
                Match match2 = new Match(table.getTeams().get(teamsIndexes.get("indexGuestTeam") - 1), table.getTeams().get(teamsIndexes.get("indexHomeTeam") - 1));

                for (int i = 0; i < matchArrayIndex; i++) {
                    if (table.getMatches().get(i).equals(match1) || table.getMatches().get(i).equals(match2)) {
                        matchAlreadyArranged = true;
                        break;
                    }
                }

                if(matchAlreadyArranged){
                    ASCIIArt.fail();
                    System.out.println("\n" + "The matches between these two team have already been arranged. Try again");
                    matchAlreadyArranged = false;
                    positions = TournamentUtils.arrayListIntegerInitializer(4);
                    teamsIndexes = selectDates(positions, table, matchCounter, matchDayCounter, phase);
                }

            }while(matchAlreadyArranged);
        }

        //Teams for the first Match of this MatchDay
        table.getMatches().get(matchArrayIndex).setHomeTeam(table.getTeams().get(teamsIndexes.get("indexHomeTeam") - 1));
        table.getMatches().get(phaseTwoMatchArrayIndex).setGuestTeam(table.getTeams().get(teamsIndexes.get("indexHomeTeam") - 1));
        //Automatically their revanche match
        table.getMatches().get(matchArrayIndex).setGuestTeam(table.getTeams().get(teamsIndexes.get("indexGuestTeam") - 1));
        table.getMatches().get(phaseTwoMatchArrayIndex).setHomeTeam(table.getTeams().get(teamsIndexes.get("indexGuestTeam") - 1));

        //Teams for the second Match of this MatchDay are the ones left
        table.getMatches().get(matchArrayIndex + 1).setHomeTeam(table.getTeams().get(positions.get(0) - 1));
        table.getMatches().get(phaseTwoMatchArrayIndex + 1).setGuestTeam(table.getTeams().get(positions.get(0) - 1));
        //Automatically their revanche match
        table.getMatches().get(matchArrayIndex + 1).setGuestTeam(table.getTeams().get(positions.get(1) - 1));
        table.getMatches().get(phaseTwoMatchArrayIndex + 1).setHomeTeam(table.getTeams().get(positions.get(1) - 1));

        ////////////////////////////////////////////Run Matches\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        //First Match of the MatchDay, Phase 1
        //Enter Score
        String askForHTeamScore1 = askForMatchInfoGroupStage(SCORE, table.getMatches().get(matchArrayIndex).getHomeTeam().getName(),
                matchCounter, matchDayCounter, table.getTableName().name(), phase);
        String askForGTeamScore1 = askForMatchInfoGroupStage(SCORE, table.getMatches().get(matchArrayIndex).getGuestTeam().getName(),
                matchCounter, matchDayCounter, table.getTableName().name(), phase);
        HashMap<String,Integer> scores1 = TournamentUtils.enterScore(askForHTeamScore1, askForGTeamScore1);
        //Run match
        table.getMatches().get(matchArrayIndex).runGroupStageMatch(scores1.get("goalsHomeTeam"), scores1.get("goalsGuestTeam"));

        System.out.println("\n" + "The two teams left will play against each other");

        //Second Match of the MatchDay, Phase 1
        System.out.println("\n" + table.getMatches().get(matchArrayIndex + 1).overview());
        //Enter Score
        String askForHTeamScore2 = askForMatchInfoGroupStage(SCORE, table.getMatches().get(matchArrayIndex + 1).getHomeTeam().getName(),
                matchCounter + 1, matchDayCounter, table.getTableName().name(), phase);
        String askForGTeamScore2 = askForMatchInfoGroupStage(SCORE, table.getMatches().get(matchArrayIndex + 1).getGuestTeam().getName(),
                matchCounter + 1, matchDayCounter, table.getTableName().name(), phase);
        HashMap<String,Integer> scores2 = TournamentUtils.enterScore(askForHTeamScore2, askForGTeamScore2);
        //Run match
        table.getMatches().get(matchArrayIndex + 1).runGroupStageMatch(scores2.get("goalsHomeTeam"), scores2.get("goalsGuestTeam"));

        phase++;
        System.out.println("\n" + "And also for the revanche games:");

        //First Match of the MatchDay, Phase 2
        System.out.println("\n" + table.getMatches().get(phaseTwoMatchArrayIndex).overview());
        //Enter Score
        String askForHTeamScore3 = askForMatchInfoGroupStage(SCORE, table.getMatches().get(phaseTwoMatchArrayIndex).getHomeTeam().getName(),
                matchCounter, phaseTwoMatchDayCounter, table.getTableName().name(), phase);
        String askForGTeamScore3 = askForMatchInfoGroupStage(SCORE, table.getMatches().get(phaseTwoMatchArrayIndex).getGuestTeam().getName(),
                matchCounter, phaseTwoMatchDayCounter, table.getTableName().name(), phase);
        HashMap<String,Integer> scores3 = TournamentUtils.enterScore(askForHTeamScore3, askForGTeamScore3);
        //Run match
        table.getMatches().get(phaseTwoMatchArrayIndex).runGroupStageMatch(scores3.get("goalsHomeTeam"), scores3.get("goalsGuestTeam"));

        //Second Match of the MatchDay, Phase 2
        System.out.println("\n" + table.getMatches().get(phaseTwoMatchArrayIndex + 1).overview());
        //Enter Score
        String askForHTeamScore4 = askForMatchInfoGroupStage(SCORE, table.getMatches().get(phaseTwoMatchArrayIndex + 1).getHomeTeam().getName(),
                matchCounter + 1, phaseTwoMatchDayCounter, table.getTableName().name(), phase);
        String askForGTeamScore4 = askForMatchInfoGroupStage(SCORE, table.getMatches().get(phaseTwoMatchArrayIndex + 1).getGuestTeam().getName(),
                matchCounter + 1, phaseTwoMatchDayCounter, table.getTableName().name(), phase);
        HashMap<String,Integer> scores4 = TournamentUtils.enterScore(askForHTeamScore4, askForGTeamScore4);
        //Run match
        table.getMatches().get(phaseTwoMatchArrayIndex + 1).runGroupStageMatch(scores4.get("goalsHomeTeam"), scores4.get("goalsGuestTeam"));

    }

    public HashMap<String,Integer> selectDates(List<Integer> positions, Table table, int matchCounter, int matchDayCounter, int phase){

        HashMap<String,Integer> teamsIndexes = new HashMap<>();
        String result;
        String input;

        do {
            System.out.println(askForMatchInfoGroupStage(HOME_TEAM,"", matchCounter, matchDayCounter,
                    table.getTableName().name(), phase));

            input = KickOff.scanner.nextLine();
            result = Validator.intCheck(input, positions);
            if(!result.equals(VALID)) {
                ASCIIArt.fail();
                System.out.println("\n" + result + ". Try again");
            }

        } while (!result.equals(VALID));

        int indexHomeTeam = Integer.parseInt(input);
        positions.remove(positions.indexOf(indexHomeTeam));

        do {
            System.out.println(askForMatchInfoGroupStage(GUEST_TEAM,"", matchCounter, matchDayCounter,
                    table.getTableName().name(), phase));

            input = KickOff.scanner.nextLine();
            result = Validator.intCheck(input, positions);
            if(!result.equals(VALID)){
                ASCIIArt.fail();
                System.out.println("\n" + result + ". Try again");
            }

        } while (!result.equals(VALID));
        int indexGuestTeam = Integer.parseInt(input);
        positions.remove(positions.indexOf(indexGuestTeam));

        teamsIndexes.put("indexHomeTeam",indexHomeTeam);
        teamsIndexes.put("indexGuestTeam",indexGuestTeam);

        return teamsIndexes;
    }

    public String askForMatchInfoGroupStage(String whatToAskFor, String teamName, int matchCounter, int matchDayCounter, String tableName, int phase){

        StringBuilder sb = new StringBuilder();

        sb.append(System.lineSeparator());
        sb.append("Enter ");

        if(whatToAskFor.equals(SCORE)){
            sb.append(whatToAskFor);
            sb.append(" for ");
            sb.append(teamName);
        }else{
            sb.append(whatToAskFor);
        }

        sb.append(" for the match ");
        sb.append(matchCounter);
        sb.append(" of the MatchDay ");
        sb.append(matchDayCounter);
        sb.append(" of Table ");
        sb.append(tableName);
        sb.append(", Phase ");
        sb.append(phase);

        return sb.toString();
    }

    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers(){

        TournamentUtils.initializeQuarterFinalsMatches(getQuarterFinals());
        List<Team> quarterFinalsTeamsRankOne = new ArrayList<>();
        List<Team> quarterFinalsTeamsRankTwo = new ArrayList<>();

        for (int i = 0 ; i < TABLES ; i++){
            getTables().get(i).getTeams().sort(Collections.reverseOrder());

            quarterFinalsTeamsRankOne.add(getTables().get(i).getTeams().get(0));
            quarterFinalsTeamsRankTwo.add(getTables().get(i).getTeams().get(1));
        }

        for (int i = 0, j = 3; i <= 3 && j >= 0; i++, j--){

            Team teamRankA = quarterFinalsTeamsRankOne.get(i);
            Team teamRankB = quarterFinalsTeamsRankTwo.get(j);

            getQuarterFinals().get(i).setHomeTeam(teamRankA);
            getQuarterFinals().get(i).setGuestTeam(teamRankB);
            //Revanche match
            getQuarterFinals().get(i + 4).setHomeTeam(teamRankB);
            getQuarterFinals().get(i + 4).setGuestTeam(teamRankA);
        }
    }

    @Override
    public void proceedToNextRound() {
        
        String input;
        boolean proceed = false;

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
                    QuarterFinals quarterFinals = new QuarterFinals(
                            getTables(), getMatchDays(),
                            getQuarterFinals(), getSemiFinals(),
                            getFinalMatch(), getChampionTeam());
                    quarterFinals.start();
                    proceed = true;
                    break;
                case "2":
                    report();
                    ASCIIArt.next();
                    break;
                case "3":
                    overview();
                    ASCIIArt.next();
                    break;
                default:
                    ASCIIArt.fail();
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
        getTables().forEach(Table::printTable);
    }

    @Override
    public void report() {

        System.out.println("*******************GROUP STAGE*********************" + "\n");

        System.out.println("*********************PHASE 1***********************" + "\n");

        for(int i = 0; i < MATCHDAYS_GROUP_STAGE /2 ; i++){
            System.out.println("---------------" + getMatchDays().get(i).toString() + "---------------" + "\n");
            for (Table table :  getTables()){
                System.out.println("Table " + table.getTableName());
                for(Match match : table.getMatches()){
                    if(match.getMatchDay() == getMatchDays().get(i)){
                        System.out.println(match);
                    }
                }
                System.out.println();
            }
        }

        System.out.println("*********************PHASE 2***********************" + "\n");

        for(int i = (MATCHDAYS_GROUP_STAGE /2); i < MATCHDAYS_GROUP_STAGE; i++){
            System.out.println("---------------" + getMatchDays().get(i).toString() + "---------------" + "\n");
            for (Table table :  getTables()){
                System.out.println("Table " + table.getTableName());
                for(Match match : table.getMatches()){
                    if(match.getMatchDay() == getMatchDays().get(i)){
                        System.out.println(match);
                    }
                }
                System.out.println();
            }
        }
    }

}
