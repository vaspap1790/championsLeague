package main.model;

import main.model.Match;

import java.util.List;

public interface KnockOut {

    void setMatchDaysAuto();

    void selectDates(List<Match> matchesListCopy);

    int getMatchDayIndexFromUserInput(Integer input);

}
