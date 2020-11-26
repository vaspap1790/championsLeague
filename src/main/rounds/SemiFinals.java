package main.rounds;

import main.KickOff;
import main.model.Match;
import main.utils.ASCIIArt;
import main.utils.TournamentUtils;

import java.util.Random;

import static main.Globals.*;

public class SemiFinals extends Round{

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
                MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS,
                dataInitializer.getMatchDays(),dataInitializer.getMatchDates());

        Random random = new Random();

        for(int i = 0; i < dataInitializer.getSemiFinalsTeams().size()/2; i++) {

            int opponentIndex = i + 2;

            dataInitializer.getSemiFinals().get(i).setHomeTeam(dataInitializer.getSemiFinalsTeams().get(i));
            dataInitializer.getSemiFinals().get(i).setGuestTeam(dataInitializer.getSemiFinalsTeams().get(opponentIndex));
            dataInitializer.getSemiFinals().get(i).runMatch(random.nextInt(6), random.nextInt(6));

            dataInitializer.getSemiFinals().get(i+4).setHomeTeam(dataInitializer.getSemiFinalsTeams().get(opponentIndex));
            dataInitializer.getSemiFinals().get(i+4).setGuestTeam(dataInitializer.getSemiFinalsTeams().get(i));
            dataInitializer.getSemiFinals().get(i+4).runMatch(random.nextInt(6), random.nextInt(6));

        }
    }

    @Override
    public void runManual() {
        System.out.println("There are 2 Match Days to be arranged.");
        System.out.println("Then you have to enter data for two matches in every Match Day.");

        setDatesManually();
        setMatchesDetailsManually();
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setDatesManually() {
        System.out.println("Firstly, you have to set dates for the four Match Days of the Semi Finals.");
        String input;
        int counter = MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS;

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

        } while (!"back".equals(input) && counter <= (MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS));
    }

    @Override
    public void setMatchesDetailsManually() {

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
                    TournamentUtils.enterMatchInfo(counter, dataInitializer.getSemiFinals());
                    System.out.println();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        }while(!"back".equals(input) && counter < 2*MATCHDAYS_QUARTERFINALS);
    }

    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers() {
        for (int i = 0; i < dataInitializer.getSemiFinals().size()/2; i++) {
            dataInitializer.getSemiFinalsTeams().add(TournamentUtils.findQualifiedTeam(dataInitializer.getSemiFinals().get(i),
                    dataInitializer.getSemiFinals().get(i+4)));
        }
    }

    @Override
    public void proceedToNextRound() {
        String input;
        boolean proceed = false;
        Finals finals = new Finals();

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
        for(int i = 0; i < dataInitializer.getSemiFinalsTeams().size()/2; i++){
            int matchNumber = i + 1;
            int opponentIndex = i + 2;
            System.out.println(matchNumber + ". " + dataInitializer.getSemiFinalsTeams().get(i).getName()
                    + " - " + dataInitializer.getSemiFinalsTeams().get(opponentIndex).getName());
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
            for(Match match : dataInitializer.getSemiFinals()){
                if(match.getMatchDay() == dataInitializer.getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }

        System.out.println("*********************PHASE 2***********************" + "\n");

        for(int i = phaseTwoStart; i < phaseTwoEnd; i++){
            System.out.println("Match Day " + i + "\n");
            for(Match match : dataInitializer.getSemiFinals()){
                if(match.getMatchDay() == dataInitializer.getMatchDays().get(i)){
                    System.out.println(match);
                }
            }
            System.out.println();
        }
    }

}
