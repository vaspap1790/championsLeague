package test.model;

import main.model.Match;
import main.model.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private Team team1 = new Team("Team1");
    private Team team2 = new Team("Team2");
    private Team team3 = new Team("Team3");

    private Match match1 = new Match(team1, team2);
    private Match match2 = new Match(team1, team2);
    private Match match3 = new Match(team2, team1);
    private Match match4 = new Match(team1, team3);

    @Test
    void testEqualsPositive1() {
        assertTrue(match1.equals(match2));
    }

    @Test
    void testEqualsNegative1() {
        assertFalse(match1.equals(match3));
    }

    @Test
    void testEqualsNegative2() {
        assertFalse(match1.equals(match4));
    }

}