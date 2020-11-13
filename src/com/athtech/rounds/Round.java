package com.athtech.rounds;

public abstract class Round {

    public abstract void mainScreen();

    public abstract void  navigationMenu();

    public void start(){
        mainScreen();
        navigationMenu();
    };

    public abstract void  runAuto();

    public abstract void  runManual();

    public abstract void  report();

}
