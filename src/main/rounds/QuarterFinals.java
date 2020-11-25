package main.rounds;

import main.KickOff;
import main.model.Match;
import main.model.Team;
import main.utils.ASCIIArt;
import main.utils.Penalties;
import main.utils.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static main.Globals.*;

public class QuarterFinals extends Round{


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
                    printPairs();
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
        System.out.println("Select the dates for each quarter final.");
        System.out.println("Then you have to enter data for two matches in every Match Day.");

        setDatesManually();
        setMatchesDetailsManually();
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


    @Override
    public void proceedToNextRound() {

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
                    enterMatchDayDate(counter);
                    selectMatchForThisDate(counter, positions);
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        } while (!"back".equals(input) && counter <= (MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS));

    }

    public void selectMatchForThisDate(int counter, List<Integer> positions){

        printPairs();
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

    @Override
    public void setDatesAuto() {
        for (int i = 0; i < dataInitializer.getMatchDays().size(); i++){
            dataInitializer.getMatchDays().get(i).setDate(dataInitializer.getMatchDates().get(i+6));
        }
    }

    @Override
    public void setMatchesDetailsManually() {

        System.out.println("Now, you have to set the match details for the 8 matches of the four tables.");
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
                    enterMatchInfo(counter);
                    System.out.println();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
            counter++;

        }while(!"back".equals(input) && counter < 2*MATCHDAYS_QUARTERFINALS);
    }

    public Team findQualifiedTeam(Match match1, Match match2){

        Team qualifiedTeam = new Team();
        int team1Goals = match1.getHomeTeam().getGoalsFor() + match2.getGuestTeam().getGoalsFor();
        int team2Goals = match1.getGuestTeam().getGoalsFor() + match2.getHomeTeam().getGoalsFor();

        if(team1Goals > team2Goals){
            qualifiedTeam = match1.getHomeTeam();
        }
        else if (team1Goals < team2Goals){
            qualifiedTeam = match2.getHomeTeam();
        }
        else{

            int team1GoalsAway = match2.getGuestTeam().getGoalsFor();
            int team2GoalsAway = match2.getHomeTeam().getGoalsFor();

            if(team1GoalsAway > team2GoalsAway){
                qualifiedTeam = match1.getHomeTeam();
            }
            else if (team1GoalsAway < team2GoalsAway){
                qualifiedTeam = match2.getHomeTeam();
            }
            else{
                System.out.println(match1);
                System.out.println(match2);
                System.out.println(match1.getHomeTeam() + " and " + match1.getGuestTeam() +
                        " need to solve their differences in Penalties!");
                System.out.println("If you want to manually run penalties, type.........1");
                System.out.println("If you want penalties to run automatically, type....2");

                String input;
                Penalties penalties = new Penalties(match1.getHomeTeam(), match1.getGuestTeam());
                boolean penaltiesPlayed = false;
                do {
                    input = KickOff.scanner.nextLine();
                    switch (input) {
                        case "1":
                            penalties.runManually();
                            System.out.println(penalties);
                            qualifiedTeam = penalties.getWinningTeam();
                            penaltiesPlayed = true;
                            break;
                        case "2":
                            penalties.runAuto();
                            System.out.println(penalties);
                            qualifiedTeam = penalties.getWinningTeam();
                            penaltiesPlayed = true;
                            break;
                        default:
                            System.out.println("\n" + "Invalid input. Try again." + "\n");
                            break;
                    }
                }while(!penaltiesPlayed);
            }
        }

        return qualifiedTeam;
    }

    @Override
    public void setQualifiers() {
        for (int i = 0; i < dataInitializer.getQuarterFinals().size()/2; i++) {
            dataInitializer.getSemiFinalsTeams().add(findQualifiedTeam(dataInitializer.getQuarterFinals().get(i),
                                                                        dataInitializer.getQuarterFinals().get(i+4)));
        }
    }

    public void enterMatchInfo(int counter){

        String input;

        //Goals Home Team
        do {
            System.out.println("\n" + "Enter score for "+ dataInitializer.getQuarterFinals().get(counter).getHomeTeam() + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, 0, 7).equals(VALID));
        int goalsH = Integer.parseInt(input);

        //Goals Guest Team
        do {
            System.out.println("\n" + "Enter score for " + dataInitializer.getQuarterFinals().get(counter).getGuestTeam() + "\n");
            input = KickOff.scanner.nextLine();
        } while (!Validator.intCheck(input, 0, 7).equals(VALID));
        int goalsG = Integer.parseInt(input);

        //Run match1
        dataInitializer.getQuarterFinals().get(counter).runMatch(goalsH,goalsG);
    }

    public void printPairs(){
        for(int i = 0; i < dataInitializer.getQuarterFinalsTeams().size()/2; i++){
            int matchNumber = i + 1;
            int opponentIndex = i + 4;
            System.out.println(matchNumber + ". " + dataInitializer.getQuarterFinalsTeams().get(i).getName()
                                           + " - " + dataInitializer.getQuarterFinalsTeams().get(opponentIndex).getName());
        }
    }

}
