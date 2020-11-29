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

public class SemiFinals extends Round{

    //Constructor
    public SemiFinals(List<Table> tables, List<MatchDay> matchDays,
                      List<Match> quarterFinals, List<Match> semiFinals,
                      Match finalMatch, Team championTeam) {
        super(tables, matchDays, quarterFinals, semiFinals, finalMatch, championTeam);
    }

    public SemiFinals(List<Match> semiFinals, List<MatchDay> matchDays) {
        super.setSemiFinals(semiFinals);
        super.setMatchDays(matchDays);
    }

    ///////////////////////////////////////////////Start Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void mainScreen() {
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("*******************************************************************************************");
        System.out.println("**                                     Semi Finals                                       **");
        System.out.println("*******************************************************************************************");
    }

    @Override
    public void navigationMenu() {

        String input;
        boolean proceed = false;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to see the Semi finals couples, type......................................'1'-");
            System.out.println("-If you want to run Semi Finals, type..................................................'2'-");
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
            System.out.println("-If you want Semi Finals automatically, type...........................................'2'-");
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

        TournamentUtils.setDatesAuto(MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS,
                MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS, getMatchDays());
        setMatchDaysAuto();

        Random random = new Random();
        getSemiFinals().forEach(match -> match.runMatch(random.nextInt(6),random.nextInt(6)));
    }

    @Override
    public boolean runManual() {
        return setDatesManually();
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public boolean setDatesManually() {

        System.out.println("Firstly, you have to set dates for the four Match Days of the Semi Finals.");
        String input;
        int matchDayCounter = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + 1;
        int matchDayIndex = matchDayCounter - 1;
        int matchDayIndexEnd = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS;
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

        System.out.println("\n" + "You successfully arranged the MathDay Dates for the Semi Finals!");

        if(setMatchesDetailsManually()){
            System.out.println("\n" + "You successfully arranged all matches for the Semi Finals!");
            return true;
        }else{
            TournamentUtils.resetMatchDaysDates(matchDayIndex, matchDayIndexEnd, getMatchDays());
            return false;
        }
    }

    @Override
    public boolean setMatchesDetailsManually() {

        System.out.println("\n" + "Now, you have to set the match details for the 4 matches of the Semi Finals.");
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
                    System.out.println("\n" + "SemiFinals Game " + matchCounter);
                    System.out.println(getSemiFinals().get(matchIndex).overview() + "\n");

                    scores = enterScore(askForMatchScore(getSemiFinals().get(matchIndex).getHomeTeam().getName()),
                            askForMatchScore(getSemiFinals().get(matchIndex).getGuestTeam().getName()));

                    getSemiFinals().get(matchIndex).runMatch(scores.get("goalsHomeTeam"), scores.get("goalsGuestTeam"));
                    matchCounter++;
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }

        }while(!"back".equals(input) && matchCounter <= SEMIFINALS_MATCHES);

        if("back".equals(input)){
            TournamentUtils.initializeSemiFinalsMatches(getSemiFinals());
            return false;
        }

        return true;
    }

    public void setMatchDaysAuto(){

        int start = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS;
        int end = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS;

        for (int matchDayIndex = start, matchIndex = 0 ;
             matchDayIndex < end && matchIndex < 4 ;
             matchDayIndex++, matchIndex++){

            getSemiFinals().get(matchIndex).setMatchDay(getMatchDays().get(matchDayIndex));
        }
    }

    public void selectDates(){

        List<Integer> positions = TournamentUtils.arrayListIntegerInitializer(2);
        List<Integer> results = new ArrayList<>();

        int semiFinalsIndex = 0;
        int semiFinalsIndexThreshold = 2;
        int matchDayCounterIndex = 10;
        int matchDayCounterPhaseTwoIndex = matchDayCounterIndex + 2;

        String input;
        String result;

        do {
            System.out.println("\n" + "For the Match " + getSemiFinals().get(semiFinalsIndex).overview() + " type:");
            System.out.println("'1' to take place in " + getMatchDays().get(matchDayCounterIndex) + " and the revanche match on " + getMatchDays().get(matchDayCounterPhaseTwoIndex));
            System.out.println("'2' to take place in " + getMatchDays().get(matchDayCounterIndex + 1) + " and the revanche match on " + getMatchDays().get(matchDayCounterPhaseTwoIndex + 1));

            input = KickOff.scanner.nextLine();
            result = Validator.intCheck(input, positions);
            if (!result.equals(VALID)) System.out.println(result + ". Try again");

        } while (!result.equals(VALID));

        results.add(Integer.parseInt(input));
        positions.remove(positions.indexOf(Integer.parseInt(input)));
        results.add(positions.get(0));


        for (int i = 0; i < semiFinalsIndexThreshold ; i++) {
            getSemiFinals().get(i).setMatchDay(getMatchDays().get(getMatchDayIndexFromUserInput(results.get(i))));
            getSemiFinals().get(i + 2).setMatchDay(getMatchDays().get(getMatchDayIndexFromUserInput(results.get(i)) + 2));
        }

        System.out.println("So, the second Semi final will take place on the other Match Day of the first Semi Finals Phase");
        System.out.println("You successfully arranged the Dates for the SemiFinals!");
    }

    public int getMatchDayIndexFromUserInput(Integer input){
        if(input == 1) return 10;
        else return 11;
    }

    public String askForMatchScore(String teamName){

        StringBuilder sb = new StringBuilder();

        sb.append(System.lineSeparator());
        sb.append("Enter score for ");
        sb.append(teamName);

        return sb.toString();
    }


    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers() {

        TournamentUtils.initializeSemiFinalsMatches(getSemiFinals());

        Team team1 = TournamentUtils.findQualifiedTeam(getSemiFinals().get(0), getSemiFinals().get(2));
        Team team2 = TournamentUtils.findQualifiedTeam(getSemiFinals().get(1), getSemiFinals().get(3));

        getFinalMatch().setHomeTeam(team1);
        getFinalMatch().setGuestTeam(team2);
    }

    @Override
    public void proceedToNextRound() {

        String input;
        boolean proceed = false;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to proceed to the Final, type.............................................'1'-");
            System.out.println("-If you want to see the matches of the SemiFinals, type................................'2'-");
            System.out.println("-If you want to end the Tournament, type...............................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    setQualifiers();
                    Finals finals = new Finals(
                            getTables(), getMatchDays(),
                            getQuarterFinals(), getSemiFinals(),
                            getFinalMatch(), getChampionTeam());
                    finals.start();
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
        for (int i = 0; i < SEMIFINALS_MATCHES/2; i++) {
            int number = i + 1;
            System.out.println(number + ". " + getSemiFinals().get(i).overview());
        }
    }

    @Override
    public void report() {

        int phaseOneStart = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS;
        int phaseTwoStart = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS/2;
        int phaseTwoEnd = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS;

        System.out.println("******************SEMI FINALS**********************" + "\n");

        System.out.println("*********************PHASE 1***********************" + "\n");

        for(int i = phaseOneStart; i < phaseTwoStart ; i++){
            System.out.println("----------" + getMatchDays().get(i).toString() + "----------" + "\n");
            for(Match match : getSemiFinals()){
                if(match.getMatchDay() == getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }

        System.out.println("*********************PHASE 2***********************" + "\n");

        for(int i = phaseTwoStart; i < phaseTwoEnd; i++){
            System.out.println("----------" + getMatchDays().get(i).toString() + "----------" + "\n");
            for(Match match : getSemiFinals()){
                if(match.getMatchDay() == getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }
    }

}
