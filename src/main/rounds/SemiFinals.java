package main.rounds;

import main.KickOff;
import main.model.Match;
import main.utils.ASCIIArt;

import static main.Globals.*;

public class SemiFinals extends Round{


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
                    showCouples();
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

    private void showCouples() {
    }

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

    }

    @Override
    public void runManual() {

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

    @Override
    public void proceedToNextRound() {

    }

    @Override
    public void setDatesAuto() {
        for (int i = 0; i < dataInitializer.getMatchDays().size(); i++){
            dataInitializer.getMatchDays().get(i).setDate(dataInitializer.getMatchDates().get(i+10));
        }
    }

    @Override
    public void setMatchesDetailsManually() {

    }

    @Override
    public void setQualifiers() {

    }

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
                    enterMatchDayDate(counter);
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        } while (!"back".equals(input) && counter <= (MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS));
    }
}
