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

    private Table table = new Table(TableName.A, Arrays.asList(new Team("team1"),
                                                                new Team("team2"),
                                                                new Team("team3"),
                                                                new Team("team4")));

    @Test
    void sortTeams() {
        table.getTeams().get(3).setGamesWon(3);
        table.getTeams().get(0).setGamesWon(2);
        table.getTeams().get(1).setGamesWon(1);

       table.getTeams().sort(Collections.reverseOrder());

        assertEquals("team4",table.getTeams().get(0).getName());
        assertEquals("team1",table.getTeams().get(1).getName());
    }

    //TODO//////////////////////////////////////////////

    @Test
    void getQualificationTeams() {

        table.getTeams().get(3).setGamesWon(3);
        table.getTeams().get(0).setGamesWon(2);
        table.getTeams().get(1).setGamesWon(1);

        List<Team> qualificationTeams = new ArrayList<>(2);

        table.getTeams().sort(Collections.reverseOrder());

        qualificationTeams.add(table.getTeams().get(0));
        qualificationTeams.add(table.getTeams().get(1));

        assertEquals("team4",qualificationTeams.get(0).getName());
        assertEquals("team1",qualificationTeams.get(1).getName());
    }

}