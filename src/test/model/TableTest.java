package test.model;

import main.model.Table;
import main.model.TableName;
import main.model.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private final Table TABLE = new Table(TableName.A, Arrays.asList(new Team("team1"),
            new Team("team2"), new Team("team3"), new Team("team4")));

    @Test
    void sortTeamsTest() {

        TABLE.getTeams().get(3).setGamesWon(3);
        TABLE.getTeams().get(0).setGamesWon(2);
        TABLE.getTeams().get(1).setGamesWon(1);

       TABLE.getTeams().sort(Collections.reverseOrder());

        assertEquals("team4", TABLE.getTeams().get(0).getName());
        assertEquals("team1", TABLE.getTeams().get(1).getName());
    }

    @Test
    void getQualificationTeamsTest() {

        TABLE.getTeams().get(3).setGamesWon(3);
        TABLE.getTeams().get(0).setGamesWon(2);
        TABLE.getTeams().get(1).setGamesWon(1);

        List<Team> qualificationTeams = new ArrayList<>(2);

        TABLE.getTeams().sort(Collections.reverseOrder());

        qualificationTeams.add(TABLE.getTeams().get(0));
        qualificationTeams.add(TABLE.getTeams().get(1));

        assertEquals("team4", qualificationTeams.get(0).getName());
        assertEquals("team1", qualificationTeams.get(1).getName());
    }

}