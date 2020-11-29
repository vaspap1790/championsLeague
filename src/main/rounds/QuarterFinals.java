package main.rounds;

import main.KickOff;
import main.model.Match;
import main.model.MatchDay;
import main.model.Table;
import main.model.Team;
import main.utils.ASCIIArt;
import main.utils.TournamentUtils;
import main.utils.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static main.resources.Globals.*;
import static main.utils.TournamentUtils.enterScore;

public class QuarterFinals extends Round{

    //Constructor
    public QuarterFinals(List<Table> tables, List<MatchDay> matchDays,
                         List<Match> quarterFinals, List<Match> semiFinals,
                         Match finalMatch, Team championTeam) {
        super(tables, matchDays, quarterFinals, semiFinals, finalMatch, championTeam);
    }

    public QuarterFinals(List<Match> quarterFinals, List<MatchDay> matchDays) {
        super.setQuarterFinals(quarterFinals);
        super.setMatchDays(matchDays);
    }

    ///////////////////////////////////////////////Start Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void mainScreen() {
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("*******************************************************************************************");
        System.out.println("**                                    Quarter Finals                                     **");
        System.out.println("*******************************************************************************************");
    }

    @Override
    public void navigationMenu() {

        String input;
        boolean proceed = false;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to see the Quarter finals couples, type...................................'1'-");
            System.out.println("-If you want to run Quarter Finals, type...............................................'2'-");
            System.out.println("-If you want to end the Tournament, type...............................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    overview();
                    break;
                case "2":
                    proceed = true;
                    break;
                default:
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
    public void selectMode() {

        String input;
        boolean modeSelected = false;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to enter dates and scores manually, type..................................'1'-");
            System.out.println("-If you want Quarter Finals automatically, type........................................'2'-");
            System.out.println("-If you want to go back, type..........................................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    modeSelected = runManual();
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

        if(modeSelected){
            proceedToNextRound();
        }

        if("0".equals(input)){
            ASCIIArt.end();
        }
    }

    @Override
    public void runAuto() {

        TournamentUtils.setDatesAuto(MATCHDAYS_GROUP_STAGE,MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS, getMatchDays());
        setMatchDaysAuto();

        Random random = new Random();
        getQuarterFinals().forEach(match -> match.runMatch(random.nextInt(6),random.nextInt(6)));

    }

    @Override
    public boolean runManual() {
        return setDatesManually();
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public boolean setDatesManually() {

        System.out.println("Firstly, you have to set dates for the four Match Days of the Quarter Finals.");
        String input;
        int matchDayCounter = MATCHDAYS_GROUP_STAGE + 1;
        int matchDayIndex = matchDayCounter - 1;
        int matchDayIndexEnd = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS;
        List<LocalDate> dates;

        do {
            dates = getMatchDays().stream().map(MatchDay::getDate).collect(Collectors.toList());
            System.out.println("\n");
            System.out.println("To enter the dates type '1', or type 'back' to cancel (data will be lost).");
            input = KickOff.scanner.nextLine();

            switch (input) {
                case "back":
                    break;
                case "1":
                    TournamentUtils.enterMatchDayDate(matchDayCounter, dates, getMatchDays());
                    matchDayCounter++;
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }

        } while (!"back".equals(input) && matchDayCounter <= matchDayIndexEnd);

        if ("back".equals(input)) {
            TournamentUtils.resetMatchDaysDates(matchDayIndex, matchDayIndexEnd, getMatchDays());
            return false;
        }

        System.out.println("\n" + "You successfully arranged the MathDay Dates for the Quarter Finals!");

        if(setMatchesDetailsManually()){
            System.out.println("\n" + "You successfully arranged all matches for the Quarter Finals!");
            return true;
        }else{
            TournamentUtils.resetMatchDaysDates(matchDayIndex, matchDayIndexEnd, getMatchDays());
            return false;
        }

    }

    @Override
    public boolean setMatchesDetailsManually() {

        System.out.println("\n" + "Now, you have to set the match details for the 8 matches of the QuarterFinals.");
        String input;

        HashMap<String,Integer> scores;

        int matchCounter = 1;

        selectDates();
        System.out.println("\n" + "Time to enter the scores!");

        do {
            System.out.println("\n" + "To enter match data type '1', or type 'back' to cancel (data will be lost)");

            int matchIndex = matchCounter - 1;
            input = KickOff.scanner.nextLine();

            switch (input) {
                case "back":
                    break;
                case "1":
                    System.out.println("\n" + "QuarterFinals Game " + matchCounter);
                    System.out.println(getQuarterFinals().get(matchIndex).overview() + "\n");

                    scores = enterScore(askForMatchScore(getQuarterFinals().get(matchIndex).getHomeTeam().getName()),
                               askForMatchScore(getQuarterFinals().get(matchIndex).getGuestTeam().getName()));

                    getQuarterFinals().get(matchIndex).runMatch(scores.get("goalsHomeTeam"), scores.get("goalsGuestTeam"));
                    matchCounter++;
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }

        }while(!"back".equals(input) && matchCounter <= QUARTERFINALS_MATCHES);

        if("back".equals(input)){
            TournamentUtils.initializeQuarterFinalsMatches(getQuarterFinals());
            return false;
        }

        return true;
    }

    ////////////////////////////////////Round Specific Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void selectDates(){

        List<Integer> positions = TournamentUtils.arrayListIntegerInitializer(2);
        List<Integer> results = new ArrayList<>();

        int quarterFinalsIndex = 0;
        int quarterFinalsIndexThreshold = QUARTERFINALS_MATCHES / 2;
        int matchDayCounterIndex = 6;
        int matchDayCounterPhaseTwoIndex = matchDayCounterIndex + 2;
        int ones = 0, twos = 0;

        String input;
        String result;
        boolean equallyDistributed = false;

        do {
            do {
                do {
                    System.out.println("\n" + "For the Match " + getQuarterFinals().get(quarterFinalsIndex).overview() + " type:");
                    System.out.println("'1' to take place in " + getMatchDays().get(matchDayCounterIndex) + " and the revanche match on " + getMatchDays().get(matchDayCounterPhaseTwoIndex));
                    System.out.println("'2' to take place in " + getMatchDays().get(matchDayCounterIndex + 1) + " and the revanche match on " + getMatchDays().get(matchDayCounterPhaseTwoIndex + 1));
                    System.out.println("***Remember that two QuarterFinals should take place in each Match Day***");

                    input = KickOff.scanner.nextLine();
                    result = Validator.intCheck(input, positions);
                    if (!result.equals(VALID)) System.out.println(result + ". Try again");

                } while (!result.equals(VALID));

                results.add(Integer.parseInt(input));
                quarterFinalsIndex++;

            } while (quarterFinalsIndex < quarterFinalsIndexThreshold);

            //Check for equal distribution

            for (Integer item : results) {
                if (item == 1) ones++;
                else twos++;
            }

            if (ones == twos) equallyDistributed = true;
            else {
                System.out.println("Two QuarterFinals should take place in each Match Day, try again.");
                quarterFinalsIndex = 0;
                ones = 0;
                twos = 0;
                results.clear();
            }

        }while (!equallyDistributed);

        for (int i = 0; i < quarterFinalsIndexThreshold ; i++) {
            getQuarterFinals().get(i).setMatchDay(getMatchDays().get(getMatchDayIndexFromUserInput(results.get(i))));
            getQuarterFinals().get(i + 4).setMatchDay(getMatchDays().get(getMatchDayIndexFromUserInput(results.get(i)) + 2));
        }

        System.out.println("You successfully arranged the Dates for the QuarterFinals!");
    }

    public int getMatchDayIndexFromUserInput(Integer input){
        if(input == 1) return 6;
        else return 7;
    }

    public String askForMatchScore(String teamName){

        StringBuilder sb = new StringBuilder();

        sb.append(System.lineSeparator());
        sb.append("Enter score for ");
        sb.append(teamName);

        return sb.toString();
    }

    public void setMatchDaysAuto(){

        int end = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS;

        for (int matchDayIndex = MATCHDAYS_GROUP_STAGE, matchIndex = 0 ; matchDayIndex < end && matchIndex < 8 ;  matchDayIndex++, matchIndex = matchIndex + 2){
            getQuarterFinals().get(matchIndex).setMatchDay(getMatchDays().get(matchDayIndex));
            getQuarterFinals().get(matchIndex + 1).setMatchDay(getMatchDays().get(matchDayIndex));
        }
    }

    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers() {

        TournamentUtils.initializeSemiFinalsMatches(getSemiFinals());

        for (int semiFinalsIndex = 0, quarterFinalsIndex = 0;
             semiFinalsIndex < SEMIFINALS_MATCHES/2 && quarterFinalsIndex < 3;
             semiFinalsIndex++, quarterFinalsIndex = quarterFinalsIndex + 2) {

            Team team1 = TournamentUtils.findQualifiedTeam(getQuarterFinals().get(quarterFinalsIndex),
                    getQuarterFinals().get(quarterFinalsIndex + 4));
            Team team2 = TournamentUtils.findQualifiedTeam(getQuarterFinals().get(quarterFinalsIndex + 1),
                    getQuarterFinals().get(quarterFinalsIndex + 5));

            getSemiFinals().get(semiFinalsIndex).setHomeTeam(team1);
            getSemiFinals().get(semiFinalsIndex).setGuestTeam(team2);
            //Revanche match
            getSemiFinals().get(semiFinalsIndex + 2).setHomeTeam(team2);
            getSemiFinals().get(semiFinalsIndex + 2).setGuestTeam(team1);
        }
    }

    @Override
    public void proceedToNextRound() {

        String input;
        boolean proceed = false;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to proceed to SemiFinals, type............................................'1'-");
            System.out.println("-If you want to see the matches of the QuarterFinals, type.............................'2'-");
            System.out.println("-If you want to end the Tournament, type...............................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    setQualifiers();
                    SemiFinals semiFinals = new SemiFinals(
                            getTables(), getMatchDays(),
                            getQuarterFinals(), getSemiFinals(),
                            getFinalMatch(), getChampionTeam());
                    semiFinals.start();
                    proceed = true;
                    break;
                case "2":
                    report();
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
    public void overview(){
        for (int i = 0; i < QUARTERFINALS_MATCHES/2; i++) {
            int number = i + 1;
            System.out.println(number + ". " + getQuarterFinals().get(i).overview());
        }
    }

    @Override
    public void report() {

        int phaseTwoStart = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS/2;
        int phaseTwoEnd = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS;

        System.out.println("*****************QUARTER FINALS********************" + "\n");

        System.out.println("\n" + "*********************PHASE 1***********************" + "\n");

        for(int i = MATCHDAYS_GROUP_STAGE; i < phaseTwoStart ; i++){
            System.out.println("----------" + getMatchDays().get(i).toString() + "----------" + "\n");
            for(Match match : getQuarterFinals()){
                if(match.getMatchDay() == getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }

        System.out.println("\n" + "*********************PHASE 2***********************" + "\n");

        for(int i = phaseTwoStart; i < phaseTwoEnd; i++){
            System.out.println("----------" + getMatchDays().get(i).toString() + "----------" + "\n");
            for(Match match : getQuarterFinals()){
                if(match.getMatchDay() == getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }
    }
}
