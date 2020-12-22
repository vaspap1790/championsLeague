package main.utils;

import main.KickOff;
import main.model.Match;
import main.model.Team;

import java.util.Random;

import static main.resources.Globals.VALID;

public class Penalties {

    //Member Variables
    private final Match MATCH;
    private Team winningTeam;
    private Team losingTeam;
    private String result;
    private final Random RANDOM = new Random();

    //Constructor
    public Penalties(Match match) {
        this.MATCH = match;
    }

    //Methods
    public void runAuto(){

        int successfulPenaltiesForTeam1;
        int successfulPenaltiesForTeam2;

        do {
            successfulPenaltiesForTeam1 = RANDOM.nextInt(6);
            successfulPenaltiesForTeam2 = RANDOM.nextInt(6);
        }while(successfulPenaltiesForTeam1 == successfulPenaltiesForTeam2 || Math.abs(successfulPenaltiesForTeam1-successfulPenaltiesForTeam2) > 2);

        if(successfulPenaltiesForTeam1 > successfulPenaltiesForTeam2){
            winningTeam = MATCH.getHomeTeam();
            losingTeam = MATCH.getGuestTeam();
        }
        else {
            winningTeam = MATCH.getGuestTeam();
            losingTeam = MATCH.getHomeTeam();
        }

        result = successfulPenaltiesForTeam1 + " - " + successfulPenaltiesForTeam2;
        MATCH.setPenaltiesScore(result);
    }

    public void runManually(){

        int successfulPenaltiesForTeam1;
        int successfulPenaltiesForTeam2;
        String input;
        String score;

        do {
            do {
                System.out.println("\n" + "Enter successful penalties for "+ MATCH.getHomeTeam().getName());
                input = KickOff.scanner.nextLine();
                score = Validator.intCheck(input, 0, 10);
                if(!score.equals(VALID)){
                    ASCIIArt.fail();
                    System.out.println("\n" + score + ". Try again.");
                }
            } while (!score.equals(VALID));

            successfulPenaltiesForTeam1 = Integer.parseInt(input);

            do {
                System.out.println("\n" + "Enter successful penalties for "+ MATCH.getGuestTeam().getName());
                input = KickOff.scanner.nextLine();
                score = Validator.intCheck(input, 0, 10);
                if(!score.equals(VALID)){
                    ASCIIArt.fail();
                    System.out.println("\n" + score + ". Try again.");
                }
            } while (!score.equals(VALID));

            successfulPenaltiesForTeam2 = Integer.parseInt(input);

            if(successfulPenaltiesForTeam1 == successfulPenaltiesForTeam2){
                ASCIIArt.fail();
                System.out.println("\n" + "One team has to win! Try again");
            }

            if(Math.abs(successfulPenaltiesForTeam1-successfulPenaltiesForTeam2) > 2){
                ASCIIArt.fail();
                System.out.println("\n" + "This is not valid, if the successful penalties difference is more than two then the game is over, try again");
            }

        }while(successfulPenaltiesForTeam1 == successfulPenaltiesForTeam2 || Math.abs(successfulPenaltiesForTeam1-successfulPenaltiesForTeam2) > 2);

        if(successfulPenaltiesForTeam1 > successfulPenaltiesForTeam2){
            winningTeam = MATCH.getHomeTeam();
            losingTeam = MATCH.getGuestTeam();
        }
        else {
            winningTeam = MATCH.getGuestTeam();
            losingTeam = MATCH.getHomeTeam();
        }

        result = successfulPenaltiesForTeam1 + " - " + successfulPenaltiesForTeam2;
        MATCH.setPenaltiesScore(result);

    }

    //Getters
    public Team getWinningTeam() {
        return winningTeam;
    }

    public Team getLosingTeam() {
        return losingTeam;
    }

    //Overridden from Object
    @Override
    public String toString() {
        return "Penalties: " + MATCH.getHomeTeam().getName() + " " + result + " " + MATCH.getGuestTeam().getName();
    }
}
