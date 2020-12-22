package main.utils;

import main.model.*;

import java.time.LocalDate;
import java.util.*;

public class DataInitializer {

    //Member Variables
    private final List<MatchDay> MATCHDAYS = Arrays.asList(MatchDay.MatchDay1,MatchDay.MatchDay2,MatchDay.MatchDay3,
            MatchDay.MatchDay4,MatchDay.MatchDay5,MatchDay.MatchDay6, MatchDay.QuarterFinals1, MatchDay.QuarterFinals2,
            MatchDay.QuarterFinals3, MatchDay.QuarterFinals4, MatchDay.SemiFinals1, MatchDay.SemiFinals2, MatchDay.SemiFinals3,
            MatchDay.SemiFinals4, MatchDay.Finals);

    private static final List<String> RANDOMTEAMSGROUPA = Arrays.asList("FC Barcelona", "Liverpool FC", "Real Madrid CF", "FC Bayern Munchen");

    private static final List<String> RANDOMTEAMSGROUPB = Arrays.asList("Club Atletico de Madrid", "FC Internationale Milano", "Juventus", "Manchester United FC");

    private static final List<String> RANDOMTEAMSGROUPC = Arrays.asList("RB Leipzig", "Olympiacos FC", "FC Shakhtar Donetsk", "FC Salzburg");

    private static final List<String> RANDOMTEAMSGROUPD = Arrays.asList( "Ferencvarosi TC", "Club Brugge", "Stade Rennais FC", "FC Midtjylland");

    private final List<LocalDate> MATCHDATES = Arrays.asList(
            LocalDate.of(2020,10,20), LocalDate.of(2020,10,27),
            LocalDate.of(2020,11,3), LocalDate.of(2020,11,24),
            LocalDate.of(2020,12,1), LocalDate.of(2020,12,8),
            LocalDate.of(2021,3,16),LocalDate.of(2021,3,17),
            LocalDate.of(2021,3,23),LocalDate.of(2021,3,24),
            LocalDate.of(2021,4,13),LocalDate.of(2021,4,14),
            LocalDate.of(2021,4,20),LocalDate.of(2021,4,21),
            LocalDate.of(2021,5,5));

    //Getters
    public List<MatchDay> getMatchDays() {
        return MATCHDAYS;
    }

    public List<LocalDate> getMatchDates() {
        return MATCHDATES;
    }

    public static List<String> getRandomTeamsGroupA() {
        return RANDOMTEAMSGROUPA;
    }

    public static List<String> getRandomTeamsGroupB() {
        return RANDOMTEAMSGROUPB;
    }

    public static List<String> getRandomTeamsGroupC() {
        return RANDOMTEAMSGROUPC;
    }

    public static List<String> getRandomTeamsGroupD() {
        return RANDOMTEAMSGROUPD;
    }
}
