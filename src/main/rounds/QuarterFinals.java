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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static main.Globals.*;

public class QuarterFinals extends Round{

    //Constructor
    public QuarterFinals(List<Table> tables, List<MatchDay> matchDays,
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

        TournamentUtils.setDatesAuto(MATCHDAYS_GROUP_STAGE,MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS, getMatchDays());

        Random random = new Random();
        getQuarterFinals().forEach(match -> match.runMatch(random.nextInt(6),random.nextInt(6)));

    }

    @Override
    public boolean runManual() {
        System.out.println("There are 4 Match Days to be arranged.");
        System.out.println("Then you have to enter data for two matches in every Match Day.");

        setDatesManually();
        setMatchesDetailsManually();
        return true;
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public boolean setDatesManually() {
        System.out.println("Firstly, you have to set dates for the four Match Days of the Quarter Finals.");
        String input;
        List<Integer> positions = Arrays.asList(1,2,3,4);
        int counter = MATCHDAYS_GROUP_STAGE;
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
                    System.out.println("\n");
                    System.out.println("Match Day " + counter);
                    System.out.println("\n");
                    TournamentUtils.enterMatchDayDate(counter,dates,getMatchDays());
                    selectMatchForThisDate(counter, positions);
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        } while (!"back".equals(input) && counter <= (MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS));
        return true;

    }

    @Override
    public boolean setMatchesDetailsManually() {

        System.out.println("Now, you have to set the match details for the 8 matches of the QuarterFinals.");
        String input;
        int counter = 0;

        do {
            System.out.println("\n");
            System.out.println("To enter match data type '1', or type 'back' to cancel (data will be lost)");
            input = KickOff.scanner.nextLine();

            switch (input) {
                case "back":
                    break;
                case "1":
                    System.out.println("\n");
                    System.out.println("Match Day " + counter + " Table ");
                    System.out.println("\n");
                    TournamentUtils.enterMatchInfo(counter, getQuarterFinals());
                    System.out.println();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        }while(!"back".equals(input) && counter < 2*MATCHDAYS_QUARTERFINALS);
        return true;
    }

    ////////////////////////////////////Round Specific Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void selectMatchForThisDate(int counter, List<Integer> positions){

        overview();
        String input;

        System.out.println("Select which two matches will take place at Match day "+ counter);

        do {
            System.out.println("\n" + "Enter the number of the pair for the 1st Match of the " + counter + " Match Day");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, positions).equals(VALID));

        int indexOfMatch1 = Integer.parseInt(input);
        positions.remove(indexOfMatch1 - 1);
        getQuarterFinals().add(new Match(getMatchDays().get(counter)));

        do {
            System.out.println("\n" + "Enter the number of the pair for the 2st Match of the " + counter + " Match Day");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, positions).equals(VALID));

        int indexOfMatch2 = Integer.parseInt(input);
        positions.remove(indexOfMatch2 - 1);
        getQuarterFinals().add(new Match(getMatchDays().get(counter)));

    }

    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers() {

        TournamentUtils.initializeSemiFinalsMatches(getQuarterFinals(), getMatchDays());

        for (int i = 0; i < SEMIFINALS_MATCHES/2; i++) {

            Team team1 = TournamentUtils.findQualifiedTeam(getQuarterFinals().get(i), getQuarterFinals().get(i + 4));
            Team team2 = TournamentUtils.findQualifiedTeam(getQuarterFinals().get(i + 1), getQuarterFinals().get(i + 5));

            getSemiFinals().get(i).setHomeTeam(team1);
            getSemiFinals().get(i).setGuestTeam(team2);
            //Revanche match
            getSemiFinals().get(i + 2).setHomeTeam(team2);
            getSemiFinals().get(i + 2).setGuestTeam(team1);
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

        System.out.println("*********************PHASE 1***********************" + "\n");

        for(int i = MATCHDAYS_GROUP_STAGE; i < phaseTwoStart ; i++){
            System.out.println("Match Day " + i + "\n");
            for(Match match : getQuarterFinals()){
                if(match.getMatchDay() == getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }

        System.out.println("*********************PHASE 2***********************" + "\n");

        for(int i = phaseTwoStart; i < phaseTwoEnd; i++){
            System.out.println("Match Day " + i + "\n");
            for(Match match : getQuarterFinals()){
                if(match.getMatchDay() == getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }
    }
}
