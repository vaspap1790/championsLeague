package main.rounds;

import main.KickOff;
import main.model.Table;
import main.utils.DataInitializer;
import main.utils.Validator;

import java.time.LocalDate;


public abstract class Round {

    //Member Variables
    protected DataInitializer dataInitializer = new DataInitializer();

    //Start Methods
    public abstract void mainScreen();

    public abstract void  navigationMenu();

    public void start(){
        mainScreen();
        navigationMenu();
    }

    //Mode Methods
    public abstract void  selectMode();

    public abstract void  runAuto();

    public abstract void  runManual();

    //Utility Methods
    public abstract void setDatesAuto();

    public abstract void setDatesManually();

    public abstract void setMatchesDetailsManually();

    //Proceed Methods
    public abstract void setQualifiers();

    public abstract void proceedToNextRound();

    //Reporting Methods
    public abstract void overview();

    public abstract void  report();

}
