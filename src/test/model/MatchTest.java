package test.model;

import main.model.Match;
import main.model.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private final Team TEAM1 = new Team("Team1");
    private final Team TEAM2 = new Team("Team2");
    private final Team TEAM3 = new Team("Team3");

    private final Match MATCH1 = new Match(TEAM1, TEAM2);
    private final Match MATCH2 = new Match(TEAM1, TEAM2);
    private final Match MATCH3 = new Match(TEAM2, TEAM1);
    private final Match MATCH4 = new Match(TEAM1, TEAM3);

    @Test
    void testEqualsPositive1() {
        assertTrue(MATCH1.equals(MATCH2));
    }

    @Test
    void testEqualsNegative1() {
        assertFalse(MATCH1.equals(MATCH3));
    }

    @Test
    void testEqualsNegative2() {
        assertFalse(MATCH1.equals(MATCH4));
    }

}