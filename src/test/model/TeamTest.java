package test.model;

import main.model.Match;
import main.model.Team;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    private Team team1 = new Team("team1");
    private Team team2 = new Team("team2");
    private Team team3 = new Team("team3");

    @Test
    void getPointsPositive() {

        team1.setGamesWon(3);
        team1.setGamesLost(2);
        team1.setGamesDrawn(1);

        assertEquals(10, team1.getPoints());
    }

    @Test
    void getGoalDifferencePositive() {

        team1.setGoalsFor(3);
        team1.setGoalsAgainst(5);

        assertEquals(-2, team1.getGoalDifference());
    }

    @Test
    void compareToGreater() {

        team1.setGamesWon(3);
        team2.setGamesWon(2);

        assertEquals(1, team1.compareTo(team2));
    }

    @Test
    void compareToLess() {

        team1.setGamesWon(3);
        team2.setGamesWon(4);

        assertEquals(-1, team1.compareTo(team2));
    }

    @Test
    void compareToEquals() {

        team1.setGamesWon(0);
        team1.setGamesDrawn(0);
        team2.setGamesWon(0);
        team2.setGamesDrawn(0);

        assertEquals(0, team1.compareTo(team2));
    }

    @Test
    void getAgainstMatchesPositive() {

        team1.getMatches().add(new Match(team1, team2));
        team1.getMatches().add(new Match(team2, team1));
        team1.getMatches().add(new Match(team1, team3));

        assertEquals(2, team1.getAgainstMatches(team2).size());
        assertEquals(team1.getMatches().get(0), team1.getAgainstMatches(team2).get(0));
        assertEquals(team1.getMatches().get(1), team1.getAgainstMatches(team2).get(1));
    }

    @Test
    void resolveEqualRankingUsingAgainstGoals() {

        team1.getMatches().add(new Match(team1, team2, 3, 2));
        team1.getMatches().add(new Match(team2, team1, 0,0));

        assertEquals(1, team1.resolveEqualRanking(team2));
    }

    @Test
    void resolveEqualRankingUsingOffensiveGoals() {

        team1.setGoalsFor(10);
        team2.setGoalsFor(8);
        team1.getMatches().add(new Match(team1, team2,3,2));
        team1.getMatches().add(new Match(team2, team1,1,0));

        assertEquals(1, team1.resolveEqualRanking(team2));
    }

}