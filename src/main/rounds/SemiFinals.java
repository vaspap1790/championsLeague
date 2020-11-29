package main.rounds;

import main.KickOff;
import main.model.Match;
import main.model.MatchDay;
import main.model.Table;
import main.model.Team;
import main.utils.ASCIIArt;
import main.utils.TournamentUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static main.resources.Globals.*;

public class SemiFinals extends Round{

    //Constructor
    public SemiFinals(List<Table> tables, List<MatchDay> matchDays,
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
            System.out.println("-If you want to see the Semi finals couples, type...................................'1'-");
            System.out.println("-If you want to run Semi Finals, type...............................................'2'-");
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

        TournamentUtils.setDatesAuto(MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS,
                MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS, getMatchDays());

        Random random = new Random();
        getSemiFinals().forEach(match -> match.runMatch(random.nextInt(6),random.nextInt(6)));
    }

    @Override
    public boolean runManual() {
        System.out.println("There are 2 Match Days to be arranged.");
        System.out.println("Then you have to enter data for two matches in every Match Day.");

        setDatesManually();
        setMatchesDetailsManually();
        return true;
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public boolean setDatesManually() {
        System.out.println("Firstly, you have to set dates for the four Match Days of the Semi Finals.");
        String input;
        int counter = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS;
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
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        } while (!"back".equals(input) && counter <= (MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS));
        return true;
    }

    @Override
    public boolean setMatchesDetailsManually() {

        System.out.println("Now, you have to set the match details for the 4 matches of the Semifinals.");
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
                    //TournamentUtils.enterMatchInfo(counter, getSemiFinals());
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

        int phaseTwoStart = MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS/2;
        int phaseTwoEnd = MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS;

        System.out.println("******************SEMI FINALS**********************" + "\n");

        System.out.println("*********************PHASE 1***********************" + "\n");

        for(int i = MATCHDAYS_QUARTERFINALS; i < phaseTwoStart ; i++){
            System.out.println("Match Day " + i + "\n");
            for(Match match : getSemiFinals()){
                if(match.getMatchDay() == getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }

        System.out.println("*********************PHASE 2***********************" + "\n");

        for(int i = phaseTwoStart; i < phaseTwoEnd; i++){
            System.out.println("Match Day " + i + "\n");
            for(Match match : getSemiFinals()){
                if(match.getMatchDay() == getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }
    }

}
