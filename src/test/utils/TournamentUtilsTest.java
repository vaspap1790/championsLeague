package test.utils;

import main.model.Match;
import main.model.MatchDay;
import main.model.Table;
import main.model.Team;
import main.utils.DataInitializer;
import main.utils.TournamentUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static main.model.MatchDay.*;
import static org.junit.jupiter.api.Assertions.*;

//One of the most substantial classes because it has the most logic
//All methods that contain logic are tested
class TournamentUtilsTest {

    @Test
    void resetMatchDaysDatesTest(){

        int start = 0;
        int end = 2;

        List<MatchDay> matchDays = Arrays.asList(MatchDay1, MatchDay2, MatchDay3);
        matchDays.get(0).setDate(LocalDate.now());
        matchDays.get(1).setDate(LocalDate.now());
        matchDays.get(2).setDate(LocalDate.now());

        TournamentUtils.resetMatchDaysDates(start, end, matchDays);

        assertNull(matchDays.get(0).getDate());
        assertNull(matchDays.get(1).getDate());
        assertNotNull(matchDays.get(2).getDate());

        matchDays.get(0).setDate(null);
        matchDays.get(1).setDate(null);
        matchDays.get(2).setDate(null);
    }

    @Test
    void setDatesAutoTest(){

        int start = 0;
        int end = 2;

        List<MatchDay> matchDays = Arrays.asList(MatchDay1, MatchDay2, MatchDay3);

        TournamentUtils.setDatesAuto(start, end, matchDays);

        assertNotNull(matchDays.get(0).getDate());
        assertNotNull(matchDays.get(1).getDate());
        assertNull(matchDays.get(2).getDate());

        matchDays.get(0).setDate(null);
        matchDays.get(1).setDate(null);
        matchDays.get(2).setDate(null);
    }

    @Test
    void findQualifiedTeamTest1() {

        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        Match match1 = new Match(team1,team2,2,1);
        Match match2 = new Match(team2,team1,1,1);

        assertEquals(team1, TournamentUtils.findQualifiedTeam(match1,match2));
    }

    @Test
    void findQualifiedTeamTest2() {

        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        Match match1 = new Match(team1,team2,1,1);
        Match match2 = new Match(team2,team1,2,1);

        assertEquals(team2, TournamentUtils.findQualifiedTeam(match1,match2));
    }

    @Test
    void findQualifiedTeamStubTest() {

        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        Match match1 = new Match(team1,team2,1,1);
        Match match2 = new Match(team2,team1,1,1);

        assertEquals("Run Penalties", TournamentUtilsStub.findQualifiedTeam(match1,match2).getName());
    }

    @Test
    void runPenaltiesStubTest1(){
        //The validity of the input is checked within ValidatorTest
        //so we hard code it here to check methods rest functionality independently
        assertEquals("Run Penalties manually",TournamentUtilsStub.runPenalties("1").getName());
    }

    @Test
    void runPenaltiesStubTest2(){
        //The validity of the input is checked within ValidatorTest
        //so we hard code it here to check methods rest functionality independently
        assertEquals("Run Penalties auto",TournamentUtilsStub.runPenalties("2").getName());
    }

    @Test
    void runPenaltiesStubTest3(){
        //The validity of the input is checked within ValidatorTest
        //so we hard code it here to check methods rest functionality independently
        assertEquals("Invalid input. Penalties not played",TournamentUtilsStub.runPenalties("some string").getName());
    }

    @Test
    void cloneListTest() throws CloneNotSupportedException {

        Team team1 = new Team("team1");
        Team team2 = new Team("team2");
        List<Match> matches = Arrays.asList(new Match(team1,team2),new Match(team2,team1));

        List<Match> clone = TournamentUtils.cloneList(matches);

        assertEquals(2, clone.size());
        assertEquals(team1, clone.get(0).getHomeTeam());
        assertEquals(team1, clone.get(1).getGuestTeam());
    }

    @Test
    void enterMatchDayDateTest(){

        int matchDayCounter = 1;
        List<MatchDay> matchDays = Arrays.asList(MatchDay1, MatchDay2);

        TournamentUtilsStub.enterMatchDayDate(matchDayCounter, matchDays);

        assertNotNull(matchDays.get(0).getDate());
        assertNull(matchDays.get(1).getDate());

        matchDays.get(0).setDate(null);
        matchDays.get(1).setDate(null);
    }

    @Test
    void enterTeamsManuallyStubTest1(){

        List<Table> tables = new ArrayList<>();
        //The validity of the input is checked within ValidatorTest
        //so we hard code it here to check methods rest functionality independently
        String data = "some string";

        TournamentUtilsStub.enterTeamsManually(tables, data);
        assertNotNull(tables.get(0).getTableName());
        assertEquals(4, tables.get(0).getTeams().size());
    }

    @Test
    void enterTeamsManuallyStubTest2(){

        List<Table> tables = new ArrayList<>();
        //The validity of the input is checked within ValidatorTest
        //so we hard code it here to check methods rest functionality independently
        String data = "back";

        TournamentUtilsStub.enterTeamsManually(tables, data);
        assertNotNull(tables.get(0).getTableName());
        assertEquals(0, tables.get(0).getTeams().size());

    }

    @Test
    void enterScoreStubTest(){

        //The validity of the input is checked within ValidatorTest
        //so we hard code it here to check methods rest functionality independently
        String score1 = "4";
        String score2 = "3";

        HashMap<String,Integer> scores = TournamentUtilsStub.enterScore(score1, score2);

        assertEquals(4, scores.get("goalsHomeTeam"));
        assertEquals(3, scores.get("goalsGuestTeam"));
    }

    @Test
    void enterTablesTest(){

        List<Table> tables = new ArrayList<>();
        TournamentUtils.enterTables(tables);

        assertEquals(4, tables.size());
    }

    @Test
    void enterTeamsFromDummyDataTest(){

        List<Table> tables = new ArrayList<>();
        TournamentUtils.enterTables(tables);
        TournamentUtils.enterTeamsFromDummyData(tables);

        assertEquals(4, tables.get(0).getTeams().size());
    }

    @Test
    void arrayListIntegerInitializerTest(){

        ArrayList<Integer> list = TournamentUtils.arrayListIntegerInitializer(4);
        assertEquals(4, list.size());
    }

    @Test
    void initializeGroupStageMatchesTest(){

        List<Table> tables = new ArrayList<>();
        TournamentUtils.enterTables(tables);

        DataInitializer dataInitializer = new DataInitializer();
        TournamentUtils.initializeGroupStageMatches(tables, dataInitializer.getMatchDays());

        assertEquals(12, tables.get(0).getMatches().size());
    }

    @Test
    void initializeQuarterFinalsMatchesTest(){

        List<Match> matches = new ArrayList<>();
        TournamentUtils.initializeQuarterFinalsMatches(matches);

        assertEquals(8, matches.size());
    }

    @Test
    void initializeSemiFinalsMatchesTest(){

        List<Match> matches = new ArrayList<>();
        TournamentUtils.initializeSemiFinalsMatches(matches);

        assertEquals(4, matches.size());
    }
}