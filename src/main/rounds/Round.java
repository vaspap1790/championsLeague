package main.rounds;

import main.utils.DataInitializer;


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

    public abstract boolean  runManual();

    //Utility Methods
    public abstract boolean setDatesManually();

    public abstract boolean setMatchesDetailsManually();

    //Proceed Methods
    public abstract void setQualifiers();

    public abstract void proceedToNextRound();

    //Reporting Methods
    public abstract void overview();

    public abstract void  report();

}
