package test.utils;

import main.model.Team;
import main.utils.Penalties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PenaltiesTest {

    private Team team1 = new Team("Team1");
    private Team team2 = new Team("Team2");

    @Test
    void runAuto() {
        Penalties penalties = new Penalties(team1, team2);
        penalties.runAuto();
        System.out.println(penalties);
    }

}