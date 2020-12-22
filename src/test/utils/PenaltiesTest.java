package test.utils;

import main.model.Team;
import main.utils.Penalties;
import org.junit.jupiter.api.Test;

class PenaltiesTest {

    private final Team TEAM1 = new Team("Team1");
    private final Team TEAM2 = new Team("Team2");

    @Test
    void runAuto() {
        Penalties penalties = new Penalties(TEAM1, TEAM2);
        penalties.runAuto();
        System.out.println(penalties);
    }

}