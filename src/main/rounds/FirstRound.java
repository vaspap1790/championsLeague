package main.rounds;

import main.KickOff;

import main.utils.ASCIIArt;

public class FirstRound extends Round{

    @Override
    public void mainScreen() {

        System.out.println("\n");
        System.out.println("*******************************************************************************************");
        System.out.println("**                                      Main Menu                                        **");

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
            System.out.println("-If you want to enter all scores for all matches of Round 1 manually, type.............'1'-");
            System.out.println("-If you want Round 1 to run automatically and see the results, type....................'2'-");
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
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input));
    }

    @Override
    public void runAuto() {
        System.out.println("Auto...");
    }

    @Override
    public void runManual() {
        System.out.println("Manual...");
    }

    @Override
    public void report() {
        dataInitializer.getTables().forEach((table) -> {
            System.out.println("Table " + table.getTableName());
            table.getTeams().forEach(team -> System.out.println(team.getName()));
            System.out.println();
        });
    }

}
