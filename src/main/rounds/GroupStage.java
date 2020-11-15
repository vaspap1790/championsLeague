package main.rounds;

import main.KickOff;

import main.model.Match;
import main.model.MatchDay;
import main.model.Table;
import main.model.Team;
import main.utils.ASCIIArt;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GroupStage extends Round{

    //Member Variables
    private static final int MATCHDAYS = 6;
    private static final int TABLES = 8;
    private static final int TEAMSPERTABLE = 4;

    private List<MatchDay> matchDays = Arrays.asList(MatchDay.MatchDay1,MatchDay.MatchDay2,MatchDay.MatchDay3,
            MatchDay.MatchDay4,MatchDay.MatchDay5,MatchDay.MatchDay6);

    @Override
    public void mainScreen() {
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("*******************************************************************************************");
        System.out.println("**                                     Group Stage                                       **");
        System.out.println("*******************************************************************************************");
    }

    @Override
    public void navigationMenu() {

        String input;

        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to enter the Teams manually, type.........................................'1'-");
            System.out.println("-If you want teams to be selected automatically, type..................................'2'-");
            System.out.println("-If you want to end the Tournament, type...............................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    dataInitializer.enterTeamsManually();
                    selectMode();
                    break;
                case "2":
                    dataInitializer.enterTeamsFromDummyData();
                    selectMode();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input));
        ASCIIArt.end();
    }

    @Override
    public void selectMode(){
        String input;
        do {
            System.out.println("\n");
            System.out.println("*******************************************************************************************");
            System.out.println("-If you want to enter all scores for all matches of Group Stage manually, type.........'1'-");
            System.out.println("-If you want Group Stage to run automatically and see the results, type................'2'-");
            System.out.println("-If you want to go back, type..........................................................'0'-");
            System.out.println("*******************************************************************************************" + "\n");

            input = KickOff.scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    runManual();
                    break;
                case "2":
                    runAuto();
                    report();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input));
    }

    @Override
    public void runAuto() {

        dataInitializer.setDatesGroupStage(matchDays);

        for (Table table : dataInitializer.getTables()) {

            Random random = new Random();

            //Run MatchDay1
            Match match1 = new Match(table.getTeams().get(0),table.getTeams().get(3),matchDays.get(0));
            match1.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match1);

            Match match2 = new Match(table.getTeams().get(1),table.getTeams().get(2),matchDays.get(0));
            match2.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match2);

            //Run MatchDay2
            Match match3 = new Match(table.getTeams().get(0),table.getTeams().get(1),matchDays.get(1));
            match3.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match3);

            Match match4 = new Match(table.getTeams().get(2),table.getTeams().get(3),matchDays.get(1));
            match4.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match4);

            //Run MatchDay3
            Match match5 = new Match(table.getTeams().get(0),table.getTeams().get(2),matchDays.get(2));
            match5.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match5);

            Match match6 = new Match(table.getTeams().get(1),table.getTeams().get(3),matchDays.get(2));
            match6.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match6);

            //Run MatchDay4
            Match match7 = new Match(table.getTeams().get(3),table.getTeams().get(0),matchDays.get(3));
            match7.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match7);

            Match match8 = new Match(table.getTeams().get(2),table.getTeams().get(1),matchDays.get(3));
            match8.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match8);

            //Run MatchDay5
            Match match9 = new Match(table.getTeams().get(1),table.getTeams().get(0),matchDays.get(4));
            match9.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match9);

            Match match10 = new Match(table.getTeams().get(3),table.getTeams().get(2),matchDays.get(4));
            match10.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match10);

            //Run MatchDay6
            Match match11 = new Match(table.getTeams().get(2),table.getTeams().get(0),matchDays.get(5));
            match11.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match11);

            Match match12 = new Match(table.getTeams().get(3),table.getTeams().get(1),matchDays.get(5));
            match12.runMatch(random.nextInt(6), random.nextInt(6));
            table.getMatches().add(match12);

        }
    }

    @Override
    public void runManual() {
        System.out.println("Manual...");
    }

    @Override
    public void report() {
        dataInitializer.getTables().forEach(Table::printTable);
    }

}
