package trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {

    private final List<Player> playerBase;
    private final QuestionDeck questionDeck;
    private int turn;

    private Player currentPlayer;

    public GameBetter() {
        this.turn = 0;
        playerBase = new ArrayList<>();
        questionDeck = new QuestionDeck();
    }

    public boolean add(String playerName) {
        playerBase.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playerBase.size());
        return true;
    }

    public void roll(int roll) {
        this.currentPlayer = playerBase.get(turn);

        System.out.println(currentPlayer.getPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (shouldReleasePlayer(roll)) {
                currentPlayer.setGettingOutOfPenaltyBox(true);
                System.out.println(currentPlayer.getPlayerName() + " is getting out of the penalty box");

                currentPlayer.updatePlaceOnBoard(roll);

                System.out.println(currentPlayer.getPlayerName()
                        + "'s new location is "
                        + currentPlayer.getPlace());
                System.out.println("The category is " + getPlaceCategory(currentPlayer.getPlace()));
                askQuestion(currentPlayer);
            } else {
                System.out.println(currentPlayer.getPlayerName() + " is not getting out of the penalty box");
                currentPlayer.setGettingOutOfPenaltyBox(false);

            }

        } else {

            currentPlayer.updatePlaceOnBoard(roll);
            System.out.println(currentPlayer.getPlayerName()
                    + "'s new location is "
                    + currentPlayer.getPlace());
            System.out.println("The category is " + getPlaceCategory(currentPlayer.getPlace()));
            askQuestion(currentPlayer);
        }

    }

    private void askQuestion(Player player) {
        Category category = getPlaceCategory(player.getPlace());
        String question = questionDeck.pickQuestionByCategory(category);
        System.out.println(question);
    }


    private Category getPlaceCategory(int place) {
        return switch (place) {
            case 0, 4, 8 -> Category.Pop;
            case 1, 5, 9 -> Category.Science;
            case 2, 6, 10 -> Category.Sports;
            default -> Category.Rock;
        };
    }

    public boolean wasCorrectlyAnswered() {
        this.currentPlayer = playerBase.get(turn);
        boolean hasCurrentPlayerWon = true;

        if (isGettingOutOfPenaltyBoxIfInPenaltyBox(currentPlayer)) {
            System.out.println("Answer was correct!!!!");
            currentPlayer.incrementPurseAmount();
            System.out.println(currentPlayer.getPlayerName()
                    + " now has "
                    + currentPlayer.getPurse()
                    + " Gold Coins.");
            hasCurrentPlayerWon = currentPlayer.hasWon();
        }

        updateGameTurn();
        return hasCurrentPlayerWon;
    }

    public boolean wrongAnswer() {
        this.currentPlayer = playerBase.get(turn);
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getPlayerName() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);
        updateGameTurn();
        return true;
    }


    private void updateGameTurn() {
        turn++;
        if (turn == playerBase.size()) turn = 0;
    }

    private boolean shouldReleasePlayer(int roll) {
        return roll % 2 != 0;
    }

    private boolean isGettingOutOfPenaltyBoxIfInPenaltyBox(Player player) {
        return !player.isInPenaltyBox() || player.isGettingOutOfPenaltyBox();
    }
}
