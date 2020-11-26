package main.rounds;

import main.KickOff;
import main.model.Match;
import main.utils.ASCIIArt;
import main.utils.TournamentUtils;
import main.utils.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static main.Globals.*;

public class QuarterFinals extends Round{

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

        Random random = new Random();

        for(int i = 0; i < dataInitializer.getQuarterFinalsTeams().size()/2; i++) {

            int opponentIndex = i + 4;

            dataInitializer.getQuarterFinals().get(i).setHomeTeam(dataInitializer.getQuarterFinalsTeams().get(i));
            dataInitializer.getQuarterFinals().get(i).setGuestTeam(dataInitializer.getQuarterFinalsTeams().get(opponentIndex));
            dataInitializer.getQuarterFinals().get(i).runMatch(random.nextInt(6), random.nextInt(6));

            dataInitializer.getQuarterFinals().get(i+4).setHomeTeam(dataInitializer.getQuarterFinalsTeams().get(opponentIndex));
            dataInitializer.getQuarterFinals().get(i+4).setGuestTeam(dataInitializer.getQuarterFinalsTeams().get(i));
            dataInitializer.getQuarterFinals().get(i+4).runMatch(random.nextInt(6), random.nextInt(6));

        }
    }

    @Override
    public void runManual() {
        System.out.println("There are 4 Match Days to be arranged.");
        System.out.println("Then you have to enter data for two matches in every Match Day.");

        setDatesManually();
        setMatchesDetailsManually();
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setDatesAuto() {
        for (int i = 0; i < dataInitializer.getMatchDays().size(); i++){
            dataInitializer.getMatchDays().get(i).setDate(dataInitializer.getMatchDates().get(i+6));
        }
    }

    @Override
    public void setDatesManually() {
        System.out.println("Firstly, you have to set dates for the four Match Days of the Quarter Finals.");
        String input;
        List<Integer> positions = Arrays.asList(1,2,3,4);
        int counter = MATCHDAYS_GROUP_STAGE;

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
                    selectMatchForThisDate(counter, positions);
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        } while (!"back".equals(input) && counter <= (MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS));

    }

    @Override
    public void setMatchesDetailsManually() {

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
                    TournamentUtils.enterMatchInfo(counter, dataInitializer.getQuarterFinals());
                    System.out.println();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        }while(!"back".equals(input) && counter < 2*MATCHDAYS_QUARTERFINALS);
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
        dataInitializer.getQuarterFinals().add(new Match(dataInitializer.getMatchDays().get(counter)));

        do {
            System.out.println("\n" + "Enter the number of the pair for the 2st Match of the " + counter + " Match Day");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, positions).equals(VALID));

        int indexOfMatch2 = Integer.parseInt(input);
        positions.remove(indexOfMatch2 - 1);
        dataInitializer.getQuarterFinals().add(new Match(dataInitializer.getMatchDays().get(counter)));

    }

    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers() {
        for (int i = 0; i < dataInitializer.getQuarterFinals().size()/2; i++) {
            dataInitializer.getSemiFinalsTeams().add(TournamentUtils.findQualifiedTeam(dataInitializer.getQuarterFinals().get(i),
                                                                        dataInitializer.getQuarterFinals().get(i+4)));
        }
    }
    @Override
    public void proceedToNextRound() {
        String input;
        boolean proceed = false;
        SemiFinals semiFinals = new SemiFinals();

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
        for(int i = 0; i < dataInitializer.getQuarterFinalsTeams().size()/2; i++){
            int matchNumber = i + 1;
            int opponentIndex = i + 4;
            System.out.println(matchNumber + ". " + dataInitializer.getQuarterFinalsTeams().get(i).getName()
                                           + " - " + dataInitializer.getQuarterFinalsTeams().get(opponentIndex).getName());
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
            for(Match match : dataInitializer.getQuarterFinals()){
                if(match.getMatchDay() == dataInitializer.getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }

        System.out.println("*********************PHASE 2***********************" + "\n");

        for(int i = phaseTwoStart; i < phaseTwoEnd; i++){
            System.out.println("Match Day " + i + "\n");
            for(Match match : dataInitializer.getQuarterFinals()){
                if(match.getMatchDay() == dataInitializer.getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }
    }
}
