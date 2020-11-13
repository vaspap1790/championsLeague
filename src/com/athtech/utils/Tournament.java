package com.athtech.utils;


import com.athtech.rounds.FirstRound;

public class Tournament {

    public static void startTournament(){

        ASCIIArt.printLogo();

        FirstRound firstRound = new FirstRound();
        firstRound.start();

    }

}
