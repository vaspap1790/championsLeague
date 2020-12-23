package test.model;

import main.model.Match;
import main.model.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//One of the most substantial classes because it has the most logic
//All methods that contain logic are tested
class TeamTest {

    private final Team TEAM1 = new Team("team1");
    private final Team TEAM2 = new Team("team2");
    private final Team TEAM3 = new Team("team3");

    @Test
    void getPointsPositiveTest() {

        TEAM1.setGamesWon(3);
        TEAM1.setGamesLost(2);
        TEAM1.setGamesDrawn(1);

        assertEquals(10, TEAM1.getPoints());
    }

    @Test
    void getGoalDifferencePositiveTest() {

        TEAM1.setGoalsFor(3);
        TEAM1.setGoalsAgainst(5);

        assertEquals(-2, TEAM1.getGoalDifference());
    }

    @Test
    void compareToGreaterTest() {

        TEAM1.setGamesWon(3);
        TEAM2.setGamesWon(2);

        assertEquals(1, TEAM1.compareTo(TEAM2));
    }

    @Test
    void compareToLessTest() {

        TEAM1.setGamesWon(3);
        TEAM2.setGamesWon(4);

        assertEquals(-1, TEAM1.compareTo(TEAM2));
    }

    @Test
    void compareToEqualsTest() {

        TEAM1.setGamesWon(0);
        TEAM1.setGamesDrawn(0);
        TEAM2.setGamesWon(0);
        TEAM2.setGamesDrawn(0);

        assertEquals(0, TEAM1.compareTo(TEAM2));
    }

    @Test
    void getAgainstMatchesPositiveTest() {

        TEAM1.getMatches().add(new Match(TEAM1, TEAM2));
        TEAM1.getMatches().add(new Match(TEAM2, TEAM1));
        TEAM1.getMatches().add(new Match(TEAM1, TEAM3));

        assertEquals(2, TEAM1.getAgainstMatches(TEAM2).size());
        assertEquals(TEAM1.getMatches().get(0), TEAM1.getAgainstMatches(TEAM2).get(0));
        assertEquals(TEAM1.getMatches().get(1), TEAM1.getAgainstMatches(TEAM2).get(1));
    }

    @Test
    void resolveEqualRankingUsingAgainstGoalsTest1() {

        TEAM1.getMatches().add(new Match(TEAM1, TEAM2, 3, 2));
        TEAM1.getMatches().add(new Match(TEAM2, TEAM1, 0,0));

        assertEquals(1, TEAM1.resolveEqualRanking(TEAM2));
    }

    @Test
    void resolveEqualRankingUsingAgainstGoalsTest2() {

        TEAM1.getMatches().add(new Match(TEAM1, TEAM2, 2, 2));
        TEAM1.getMatches().add(new Match(TEAM2, TEAM1, 1,0));

        assertEquals(-1, TEAM1.resolveEqualRanking(TEAM2));
    }

    @Test
    void resolveEqualRankingUsingOffensiveGoalsTest1() {

        TEAM1.setGoalsFor(10);
        TEAM2.setGoalsFor(8);
        TEAM1.getMatches().add(new Match(TEAM1, TEAM2,3,2));
        TEAM1.getMatches().add(new Match(TEAM2, TEAM1,1,0));

        assertEquals(1, TEAM1.resolveEqualRanking(TEAM2));
    }

    @Test
    void resolveEqualRankingUsingOffensiveGoalsTest2() {

        TEAM1.setGoalsFor(7);
        TEAM2.setGoalsFor(8);
        TEAM1.getMatches().add(new Match(TEAM1, TEAM2,1,1));
        TEAM1.getMatches().add(new Match(TEAM2, TEAM1,2,2));

        assertEquals(-1, TEAM1.resolveEqualRanking(TEAM2));
    }

}