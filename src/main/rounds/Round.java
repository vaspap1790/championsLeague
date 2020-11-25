package main.rounds;

import main.KickOff;
import main.utils.DataInitializer;
import main.utils.Validator;

import java.time.LocalDate;


public abstract class Round {

    protected DataInitializer dataInitializer = new DataInitializer();

    public abstract void mainScreen();

    public abstract void  navigationMenu();

    public void start(){
        mainScreen();
        navigationMenu();
    }

    public abstract void  selectMode();

    public abstract void  runAuto();

    public abstract void  runManual();

    public abstract void  report();

    public abstract void proceedToNextRound();

    public abstract void setDatesManually();

    public abstract void setDatesAuto();

    public abstract void setMatchesDetailsManually();

    public abstract void setQualifiers();

        public void enterMatchDayDate(int matchDayCounter){
        String date;
        boolean correctFormat;

        do {
            System.out.println("\n" + "Enter Date for the " + matchDayCounter +" Match Day. ***Format YYYY-MM-DD***" + "\n");
            date = KickOff.scanner.nextLine();
            correctFormat = Validator.dateCheck(date);
        } while (!correctFormat);

        dataInitializer.getMatchDays().get(matchDayCounter).setDate(LocalDate.parse(date));
    }
}
