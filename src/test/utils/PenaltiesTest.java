package test.utils;

import main.model.Match;
import main.model.Team;
import main.utils.Penalties;
import org.junit.jupiter.api.Test;

class PenaltiesTest {

    private final Team TEAM1 = new Team("Team1");
    private final Team TEAM2 = new Team("Team2");
    private final Match MATCH = new Match(TEAM1, TEAM2);

    @Test
    void runAutoTest() {
        Penalties penalties = new Penalties(MATCH);
        penalties.runAuto();
        System.out.println(penalties);
    }

}