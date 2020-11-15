package main.rounds;

import main.utils.DataInitializer;

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

}
