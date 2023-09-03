package trivia;

public class Player {

    private int place;
    private int purse;
    private boolean inPenaltyBox;

    private boolean gettingOutOfPenaltyBox;

    private final String playerName;


    public Player(String playerName) {
        this.place = 0;
        this.purse = 0;
        this.inPenaltyBox = false;
        this.gettingOutOfPenaltyBox = false;
        this.playerName = playerName;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return gettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOutOfPenaltyBox) {
        this.gettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "place=" + place +
                ", purse=" + purse +
                ", inPenaltyBox=" + inPenaltyBox +
                ", playerName='" + playerName + '\'' +
                '}';
    }


    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }


    public void updatePlaceOnBoard(int roll) {
        this.place = this.place + roll;
        if (this.place > 11) {
            this.place = this.place - 12;
        }
    }

    public void incrementPurseAmount() {
        purse = purse + 1;
    }

    public boolean hasWon() {
        return !(this.getPurse() == 6);
    }
}
