package main.rounds;

import main.KickOff;
import main.model.MatchDay;
import main.utils.ASCIIArt;

public class Final extends Round{


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
            System.out.println("-If you want to enter date and score manually, type....................................'1'-");
            System.out.println("-If you want the Final to run automatically, type......................................'2'-");
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
        System.out.println(dataInitializer.getFinalMatch());
    }

    @Override
    public void proceedToNextRound() {
        ASCIIArt.complete();
    }

    @Override
    public void setDatesAuto(){
        dataInitializer.getMatchDays().get(11).setDate(dataInitializer.getMatchDates().get(15));
    }

    @Override
    public void setMatchesDetailsManually() {

    }

    @Override
    public void setDatesManually() {
        System.out.println("Firstly, you have to set the date for the Match Day of the Final.");
        System.out.println("\n");
        enterMatchDayDate(15);
    }
}
