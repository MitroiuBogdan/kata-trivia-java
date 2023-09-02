package trivia;

public class Player {

    private int place;
    private int purse;
    private boolean inPenaltyBox;
    private String playerName;


    public Player(String playerName) {
        this.place = 0;
        this.purse = 0;
        this.inPenaltyBox = false;
        this.playerName = playerName;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
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

    public void setPlace(int place) {
        this.place = place;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void updatePlace(int roll) {
        this.place = this.place + roll;
        if (this.place > 11) {
            this.place = this.place - 12;
        }
    }

    public void incrementPurse() {
        purse = purse + 1;
    }
}
