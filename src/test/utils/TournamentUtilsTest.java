package test.utils;

import main.model.Match;
import main.model.Team;
import main.utils.TournamentUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TournamentUtilsTest {

    @Test
    void findQualifiedTeam1() {

        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        Match match1 = new Match(team1,team2,2,1);
        Match match2 = new Match(team2,team1,1,1);

        assertEquals(team1, TournamentUtils.findQualifiedTeam(match1,match2));
    }

    @Test
    void findQualifiedTeam2() {

        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        Match match1 = new Match(team1,team2,1,1);
        Match match2 = new Match(team2,team1,2,1);

        assertEquals(team2, TournamentUtils.findQualifiedTeam(match1,match2));
    }


}