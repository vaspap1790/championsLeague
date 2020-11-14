package main.rounds;

import main.KickOff;

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
                    break;
                case "2":
                    dataInitializer.enterTeamsFromDummyData();
                    report();
                    break;
                default:
                    System.out.println("\n" + "Invalid input. Try again." + "\n");
                    break;
            }
        } while (!"0".equals(input));

        System.out.println("\n" + "******************************* You ended the Tournament *********************************");
    }

    @Override
    public void runAuto() {

    }

    @Override
    public void runManual() {

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
