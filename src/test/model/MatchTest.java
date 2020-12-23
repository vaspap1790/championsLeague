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
    void testEqualsPositiveTest1() {
        assertTrue(MATCH1.equals(MATCH2));
    }

    @Test
    void testEqualsNegativeTest1() {
        assertFalse(MATCH1.equals(MATCH3));
    }

    @Test
    void testEqualsNegativeTest2() {
        assertFalse(MATCH1.equals(MATCH4));
    }

    @Test
    void toStringTest1(){
        MATCH1.setGoalsForHTeam(2);
        MATCH1.setGoalsForGTeam(2);
        MATCH1.setPenaltiesScore("4-3");

        System.out.println(MATCH1);
    }

    @Test
    void toStringTest2(){
        MATCH1.setGoalsForHTeam(2);
        MATCH1.setGoalsForGTeam(1);

        System.out.println(MATCH1);
    }

}