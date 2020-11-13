package com.athtech.rounds;

import com.athtech.model.TableName;
import com.athtech.model.Team;
import com.athtech.utils.DummyData;

import java.util.*;

import static com.athtech.KickOff.scanner;

public class FirstRound extends Round{

    Map<TableName, List<Team>> roundData = new TreeMap<>();

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

            input = scanner.nextLine();

            switch (input) {
                case "0":
                    break;
                case "1":
                    enterTeamsManually();
                    break;
                case "2":
                    enterTeamsFromDummyData();
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

        roundData.forEach((tableName, teams) -> {
            System.out.println("Table " + tableName);
            teams.stream().forEach(team -> System.out.println(team.getName()));
            System.out.println();
        });

    }

    public void enterTables(){
        for (TableName tableName : TableName.values()){
            roundData.put(tableName, new ArrayList<Team>());
        }
    }

    public void enterTeamsManually(){

    }

    public void enterTeamsFromDummyData(){

        enterTables();
        DummyData dummyData = new DummyData();

        for (int i = 0 ; i < 8 ; i++){
            roundData.get(TableName.values()[i]).add(new Team(dummyData.randomTeamsGroupA.get(i)));
            roundData.get(TableName.values()[i]).add(new Team(dummyData.randomTeamsGroupB.get(i)));
            roundData.get(TableName.values()[i]).add(new Team(dummyData.randomTeamsGroupC.get(i)));
            roundData.get(TableName.values()[i]).add(new Team(dummyData.randomTeamsGroupD.get(i)));
        }

    }

}
