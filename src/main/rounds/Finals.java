package main.rounds;

import main.KickOff;
import main.model.Match;
import main.model.Team;
import main.utils.ASCIIArt;
import main.utils.TournamentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Finals extends Round{

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

        Random random = new Random();

        dataInitializer.getFinalMatch().setHomeTeam(dataInitializer.getFinalTeams().get(0));
        dataInitializer.getFinalMatch().setHomeTeam(dataInitializer.getFinalTeams().get(1));
        dataInitializer.getFinalMatch().runMatch(random.nextInt(6), random.nextInt(6));
    }

    @Override
    public void runManual() {
        System.out.println("There is the Finals Match Days to be arranged.");
        System.out.println("Then you have to enter the data for the Final.");

        setDatesManually();
        setMatchesDetailsManually();
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setDatesAuto(){
        dataInitializer.getMatchDays().get(11).setDate(dataInitializer.getMatchDates().get(15));
    }

    @Override
    public void setDatesManually() {
        System.out.println("Firstly, you have to set the date for the Match Day of the Final.");
        System.out.println("\n");
        TournamentUtils.enterMatchDayDate(15,dataInitializer.getMatchDays());
    }

    @Override
    public void setMatchesDetailsManually() {
        int counter = 0;
        List<Match> matches = new ArrayList<>();
        matches.add(dataInitializer.getFinalMatch());
        TournamentUtils.enterMatchInfo(counter, matches);
    }

    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers() {
        if(dataInitializer.getFinalMatch().getHomeTeam().getGoalsFor() > dataInitializer.getFinalMatch().getGuestTeam().getGoalsFor() ){
            dataInitializer.setChampionTeam(dataInitializer.getFinalMatch().getHomeTeam());
        }
        else if(dataInitializer.getFinalMatch().getHomeTeam().getGoalsFor() < dataInitializer.getFinalMatch().getGuestTeam().getGoalsFor() ){
            dataInitializer.setChampionTeam(dataInitializer.getFinalMatch().getGuestTeam());
        }else{
            System.out.println(dataInitializer.getFinalMatch());
            Team championTeam = new Team();
            TournamentUtils.runPenalties(dataInitializer.getFinalMatch().getHomeTeam(),dataInitializer.getFinalMatch().getGuestTeam(),championTeam);
            dataInitializer.setChampionTeam(championTeam);
        }
    }

    @Override
    public void proceedToNextRound() {
        ASCIIArt.complete();
    }

    ////////////////////////////////////////////Reporting Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void overview(){
        System.out.println(dataInitializer.getFinalTeams().get(0).getName()
                + " - " + dataInitializer.getFinalTeams().get(1).getName());
    }

    @Override
    public void report() {
        System.out.println(dataInitializer.getFinalMatch());
    }
}
