package main.model;

import java.util.List;

public interface KnockOut {

    void setMatchDaysAuto();

    void selectDates(List<Match> matchesListCopy);

    int getMatchDayIndexFromUserInput(Integer input);

}
