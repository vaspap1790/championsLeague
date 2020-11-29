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

public class Finals extends Round{

    //Constructor
    public Finals(List<Table> tables, List<MatchDay> matchDays,
                  List<Match> quarterFinals, List<Match> semiFinals,
                  Match finalMatch, Team championTeam) {
        super(tables, matchDays, quarterFinals, semiFinals, finalMatch, championTeam);
    }
    
    ///////////////////////////////////////////////Start Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void mainScreen() {
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("*******************************************************************************************");
        System.out.println("**                                       Final                                           **");
        System.out.println("*******************************************************************************************");
    }

    @Override
    public void navigationMenu() {
        
        String input;
        boolean proceed = false;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to see the finals teams, type.............................................'1'-");
            System.out.println("-If you want to run the Final, type....................................................'2'-");
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
            System.out.println("-If you want to enter date and score manually, type....................................'1'-");
            System.out.println("-If you want the Final to run automatically, type......................................'2'-");
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

        TournamentUtils.setDatesAuto(MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS,
                MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS + MATCHDAYS_FINALS, getMatchDays());
        setMatchDaysAuto();

        Random random = new Random();
        getFinalMatch().runMatch(random.nextInt(6), random.nextInt(6));
    }

    @Override
    public boolean runManual() {
        return setDatesManually();
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public boolean setDatesManually() {
        
        System.out.println("Firstly, you have to set the date for the Match Day of the Final.");
        String input;
        int matchDayCounter = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS + 1;
        int matchDayIndex = matchDayCounter - 1;
        int matchDayIndexEnd = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS + MATCHDAYS_FINALS;
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

        System.out.println("\n" + "You successfully arranged the MathDay Date for the Finals!");

        if(setMatchesDetailsManually()){
            System.out.println("\n" + "You successfully arranged the Finals!");
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
        boolean finalsTookPlace = false;

        HashMap<String,Integer> scores;

        setMatchDaysAuto();
        System.out.println("\n" + "Time to enter the scores!");

        do {
            System.out.println("\n" + "To enter match data type '1', or type 'back' to cancel (data will be lost)");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "back":
                    break;
                case "1":
                    System.out.println("\n" + "Finals Game");
                    System.out.println(getFinalMatch().overview() + "\n");

                    scores = enterScore(askForMatchScore(getFinalMatch().getHomeTeam().getName()),
                            askForMatchScore(getFinalMatch().getGuestTeam().getName()));

                    getFinalMatch().runMatch(scores.get("goalsHomeTeam"), scores.get("goalsGuestTeam"));
                    finalsTookPlace = true;
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }

        }while(!"back".equals(input) && !finalsTookPlace);

        if("back".equals(input)){
            getFinalMatch().setMatchDay(null);
            return false;
        }

        return true;

    }

    public String askForMatchScore(String teamName){

        StringBuilder sb = new StringBuilder();

        sb.append(System.lineSeparator());
        sb.append("Enter score for ");
        sb.append(teamName);

        return sb.toString();
    }

    public void setMatchDaysAuto(){
        getFinalMatch().setMatchDay(getMatchDays().get(14));
    }
    
    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers() {

        int homeTeamGoals = getFinalMatch().getGoalsForHTeam();
        int guestTeamGoals = getFinalMatch().getGoalsForGTeam();

        if(homeTeamGoals > guestTeamGoals){
            setChampionTeam(getFinalMatch().getHomeTeam());
        }
        else if(homeTeamGoals < guestTeamGoals){
            setChampionTeam(getFinalMatch().getGuestTeam());
        }else{
            System.out.println(getFinalMatch());
            setChampionTeam(TournamentUtils.runPenalties(getFinalMatch().getHomeTeam(),getFinalMatch().getGuestTeam()));
        }
    }

    @Override
    public void proceedToNextRound() {

        setQualifiers();

        System.out.println("\n" + "*******************************************************************************************");
        System.out.println(getChampionTeam().getName() + " is the Champion Team!!!");
        System.out.println("*******************************************************************************************" + "\n");

        String input;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to see the Finals Match, type.............................................'1'-");
            System.out.println("-If you want to see the matches of Group Stage, type...................................'2'-");
            System.out.println("-If you want to see the standings of Group Stage, type.................................'3'-");
            System.out.println("-If you want to see the Quarter Finals matches, type...................................'4'-");
            System.out.println("-If you want to see the Semi Finals matches, type......................................'5'-");
            System.out.println("-If you want to end the Tournament, type...............................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    report();
                    break;
                case "2":
                    GroupStage groupStageForReport = new GroupStage(getTables(),getMatchDays());
                    groupStageForReport.report();
                    break;
                case "3":
                    GroupStage groupStage = new GroupStage(getTables(),getMatchDays());
                    groupStage.overview();
                    break;
                case "4":
                    QuarterFinals quarterFinals = new QuarterFinals(getQuarterFinals(), getMatchDays());
                    quarterFinals.report();
                    break;
                case "5":
                    SemiFinals semiFinals = new SemiFinals(getSemiFinals(), getMatchDays());
                    semiFinals.report();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input));

        ASCIIArt.complete();
    }

    ////////////////////////////////////////////Reporting Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void overview(){
        System.out.println(getFinalMatch().overview());
    }

    @Override
    public void report() {
        System.out.println(getFinalMatch());
    }
}
