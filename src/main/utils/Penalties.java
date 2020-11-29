package main.utils;

import main.KickOff;
import main.model.Match;
import main.model.Team;

import java.util.Random;

import static main.resources.Globals.VALID;

public class Penalties {

    //Member Variables
    private Team team1;
    private Team team2;
    private Team winningTeam;
    private Team losingTeam;
    private String result;
    private Random random = new Random();

    //Constructor
    public Penalties(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    //Methods
    public void runAuto(){

        int successfulPenaltiesForTeam1;
        int successfulPenaltiesForTeam2;

        do {
            successfulPenaltiesForTeam1 = random.nextInt(6);
            successfulPenaltiesForTeam2 = random.nextInt(6);
        }while(successfulPenaltiesForTeam1 == successfulPenaltiesForTeam2 || Math.abs(successfulPenaltiesForTeam1-successfulPenaltiesForTeam2) > 2);

        if(successfulPenaltiesForTeam1 > successfulPenaltiesForTeam2){
            winningTeam = team1;
            losingTeam = team2;
        }
        else {
            winningTeam = team2;
            losingTeam = team1;
        }
        result = successfulPenaltiesForTeam1 + " - " + successfulPenaltiesForTeam2;
    }

    public void runManually(){

        int successfulPenaltiesForTeam1;
        int successfulPenaltiesForTeam2;
        String input;
        do {
            do {
                System.out.println("\n" + "Enter successful penalties for "+ team1.getName() + "\n");
                input = KickOff.scanner.nextLine();
            } while (!Validator.intCheck(input, 0, 6).equals(VALID));
            successfulPenaltiesForTeam1 = Integer.parseInt(input);

            do {
                System.out.println("\n" + "Enter successful penalties for "+ team2.getName() + "\n");
                input = KickOff.scanner.nextLine();
            } while (!Validator.intCheck(input, 0, 6).equals(VALID));
            successfulPenaltiesForTeam2 = Integer.parseInt(input);

            if(successfulPenaltiesForTeam1 == successfulPenaltiesForTeam2){
                System.out.println("One team has to win! Try again");
            }

            if(Math.abs(successfulPenaltiesForTeam1-successfulPenaltiesForTeam2) > 2){
                System.out.println("This is not valid, if the successful penalties difference is more than two then the game is over, try again");
            }

        }while(successfulPenaltiesForTeam1 == successfulPenaltiesForTeam2 || Math.abs(successfulPenaltiesForTeam1-successfulPenaltiesForTeam2) > 2);

        if(successfulPenaltiesForTeam1 > successfulPenaltiesForTeam2){
            winningTeam = team1;
            losingTeam = team2;
        }
        else {
            winningTeam = team2;
            losingTeam = team1;
        }
        result = successfulPenaltiesForTeam1 + " - " + successfulPenaltiesForTeam2;
    }

    //Getters-Setters
    public Team getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(Team winningTeam) {
        this.winningTeam = winningTeam;
    }

    public Team getLosingTeam() {
        return losingTeam;
    }

    public void setLosingTeam(Team losingTeam) {
        this.losingTeam = losingTeam;
    }

    @Override
    public String toString() {
        return "Penalties" + team1.getName() + " " + result + " " + team2.getName();
    }
}
