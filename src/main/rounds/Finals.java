package main.rounds;

import main.KickOff;
import main.model.Match;
import main.model.MatchDay;
import main.model.Table;
import main.model.Team;
import main.utils.ASCIIArt;
import main.utils.TournamentUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static main.resources.Globals.*;

public class Finals extends Round{

    //Constructor
    public Finals(List<Table> tables, List<MatchDay> matchDays,
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

        TournamentUtils.setDatesAuto(MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS,
                MATCHDAYS_GROUP_STAGE + MATCHDAYS_QUARTERFINALS + MATCHDAYS_SEMIFINALS + MATCHDAYS_FINALS, getMatchDays());

        Random random = new Random();
        getFinalMatch().runMatch(random.nextInt(6), random.nextInt(6));
    }

    @Override
    public boolean runManual() {
        System.out.println("There is the Finals Match Days to be arranged.");
        System.out.println("Then you have to enter the data for the Final.");

        setDatesManually();
        setMatchesDetailsManually();
        return true;
    }

    //////////////////////////////////////////////Utility Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public boolean setDatesManually() {
        List<LocalDate> dates = getMatchDays().stream().map(MatchDay::getDate).collect(Collectors.toList());
        System.out.println("Firstly, you have to set the date for the Match Day of the Final.");
        System.out.println("\n");
        TournamentUtils.enterMatchDayDate(15,dates,getMatchDays());
        return true;

    }

    @Override
    public boolean setMatchesDetailsManually() {
        int counter = 0;
        List<Match> matches = new ArrayList<>();
        matches.add(getFinalMatch());
        //TournamentUtils.enterMatchInfo(counter, matches);
        return true;

    }

    /////////////////////////////////////////////////Proceed Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void setQualifiers() {
        if(getFinalMatch().getHomeTeam().getGoalsFor() > getFinalMatch().getGuestTeam().getGoalsFor() ){
            setChampionTeam(getFinalMatch().getHomeTeam());
        }
        else if(getFinalMatch().getHomeTeam().getGoalsFor() < getFinalMatch().getGuestTeam().getGoalsFor() ){
            setChampionTeam(getFinalMatch().getGuestTeam());
        }else{
            System.out.println(getFinalMatch());
            Team championTeam = new Team();
            TournamentUtils.runPenalties(getFinalMatch().getHomeTeam(),getFinalMatch().getGuestTeam());
            setChampionTeam(championTeam);
        }
    }

    @Override
    public void proceedToNextRound() {
        ASCIIArt.complete();
    }

    ////////////////////////////////////////////Reporting Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public void overview(){
        System.out.println(getFinalMatch().overview());
    }

    @Override
    public void report() {
        System.out.println(getFinalMatch());
    }
}
