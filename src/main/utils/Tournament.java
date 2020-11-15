package main.utils;


import main.rounds.GroupStage;

public class Tournament {

    public static void startTournament(){

        ASCIIArt.printLogo();

        GroupStage groupStage = new GroupStage();
        groupStage.start();

    }

}
