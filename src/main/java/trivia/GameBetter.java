package trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {


    private final static int QUESTION_DECK_SIZE = 52;
    private final List<Player> playerBase;
    private final QuestionDeck questionDeck;
    private int turn;
    private Player currentPlayer;

    public GameBetter() {
        this.turn = 0;
        playerBase = new ArrayList<>();
        questionDeck = new QuestionDeck(QUESTION_DECK_SIZE);
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
            boolean shouldRelease = roll % 2 != 0;
            currentPlayer.setGettingOutOfPenaltyBox(shouldRelease);
            System.out.println(currentPlayer.getPlayerName() + (shouldRelease ? " is getting out of the penalty box" : " is not getting out of the penalty box"));

            if (shouldRelease) {
                executePlayerTurn(roll, currentPlayer);
            }
        } else {
            executePlayerTurn(roll, currentPlayer);
        }
    }

    private void executePlayerTurn(int roll, Player player) {
        player.updatePositionOnBoard(roll);
        System.out.println(player.getPlayerName() + "'s new location is " + player.getPlace());
        String question = questionDeck.pickQuestionByPlace(player.getPlace());
        System.out.println(question);
    }


    public boolean wasCorrectlyAnswered() {
        if (currentPlayer == null) {
            currentPlayer = playerBase.get(turn);
        }

        //the return values should be reversed -> i will keep like this to not modify PLayGame.class
        boolean hasCurrentPlayerWon = true;

        if (!currentPlayer.isInPenaltyBox() || currentPlayer.isGettingOutOfPenaltyBox()) {
            System.out.println("Answer was correct!!!!");
            currentPlayer.incrementPurseAmount();
            System.out.println(currentPlayer.getPlayerName() + " now has " + currentPlayer.getPurse() + " Gold Coins.");
            hasCurrentPlayerWon = currentPlayer.hasWon();
        }

        updateGameTurn();
        return hasCurrentPlayerWon;
    }

    public boolean wrongAnswer() {
        if (currentPlayer == null) {
            currentPlayer = playerBase.get(turn);
        }

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

}
