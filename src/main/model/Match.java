package main.model;

public class Match implements Cloneable {

    //Member Variables
    private Team homeTeam;
    private Team guestTeam;
    private int goalsForHTeam;
    private int goalsForGTeam;
    private MatchDay matchDay;

    //Constructors
    public Match(Team homeTeam, Team guestTeam) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
    }

    public Match(Team homeTeam, Team guestTeam, int goalsForHTeam, int goalsForGTeam) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.goalsForHTeam = goalsForHTeam;
        this.goalsForGTeam = goalsForGTeam;
    }

    public Match(Team homeTeam, Team guestTeam, MatchDay matchDay) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.matchDay = matchDay;
    }

    public Match(MatchDay matchDay) {
        this.matchDay = matchDay;
    }

    public Match() {
    }

    //Methods
    public void runMatch(int goalsForH, int goalsForG){

        goalsForHTeam = goalsForH;
        goalsForGTeam = goalsForG;
    }

    public void runGroupStageMatch(int goalsForH, int goalsForG){

        goalsForHTeam = goalsForH;
        goalsForGTeam = goalsForG;

        homeTeam.setGoalsFor(homeTeam.getGoalsFor() + goalsForH);
        homeTeam.setGoalsAgainst(homeTeam.getGoalsAgainst() + goalsForG);

        guestTeam.setGoalsFor(guestTeam.getGoalsFor() + goalsForG);
        guestTeam.setGoalsAgainst(guestTeam.getGoalsAgainst() + goalsForH);

        homeTeam.setGamesPlayed(homeTeam.getGamesPlayed() + 1);
        guestTeam.setGamesPlayed(guestTeam.getGamesPlayed() + 1);

        if(goalsForH > goalsForG){
            homeTeam.setGamesWon(homeTeam.getGamesWon() + 1);
            guestTeam.setGamesLost(guestTeam.getGamesLost() + 1);
        }

        if(goalsForH < goalsForG){
            guestTeam.setGamesWon(guestTeam.getGamesWon() + 1);
            homeTeam.setGamesLost(homeTeam.getGamesLost() + 1);
        }

        if(goalsForH == goalsForG){
            guestTeam.setGamesDrawn(guestTeam.getGamesDrawn() + 1);
            homeTeam.setGamesDrawn(homeTeam.getGamesDrawn() + 1);
        }
    }

    public String overview() {
        return homeTeam.getName() + " - " + guestTeam.getName();
    }

    //Getters-Setters
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public int getGoalsForHTeam() {
        return goalsForHTeam;
    }

    public void setGoalsForHTeam(int goalsForHTeam) {
        this.goalsForHTeam = goalsForHTeam;
    }

    public int getGoalsForGTeam() {
        return goalsForGTeam;
    }

    public void setGoalsForGTeam(int goalsForGTeam) {
        this.goalsForGTeam = goalsForGTeam;
    }

    public MatchDay getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(MatchDay matchDay) {
        this.matchDay = matchDay;
    }

    //Override Object Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return getHomeTeam().equals(match.getHomeTeam()) && getGuestTeam().equals(match.getGuestTeam());
    }

    @Override
    public String toString() {
        return homeTeam.getName() + " " + goalsForHTeam + " - " + goalsForGTeam + " " + guestTeam.getName();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
