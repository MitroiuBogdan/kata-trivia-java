package trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {

    List<Player> playerBase;

    private final QuestionDeck questionDeck;

    int turn = 0;
    boolean isGettingOutOfPenaltyBox;

    public GameBetter() {
        playerBase = new ArrayList<>();
        questionDeck = new QuestionDeck();
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        playerBase.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playerBase.size());
        return true;
    }

    public int howManyPlayers() {
        return playerBase.size();
    }

    public void roll(int roll) {
        Player currentPlayer = playerBase.get(turn);

        System.out.println(currentPlayer.getPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(currentPlayer.getPlayerName() + " is getting out of the penalty box");

                currentPlayer.updatePlace(roll);

                System.out.println(currentPlayer.getPlayerName()
                        + "'s new location is "
                        + currentPlayer.getPlace());
                System.out.println("The category is " + getCategoryByPlace(currentPlayer.getPlace()));
                askQuestion(currentPlayer);
            } else {
                System.out.println(currentPlayer.getPlayerName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;

            }

        } else {

            currentPlayer.updatePlace(roll);
            System.out.println(currentPlayer.getPlayerName()
                    + "'s new location is "
                    + currentPlayer.getPlace());
            System.out.println("The category is " + getCategoryByPlace(currentPlayer.getPlace()));
            askQuestion(currentPlayer);
        }

    }

    private void askQuestion(Player player) {
        Category category = getCategoryByPlace(player.getPlace());
        String question = questionDeck.pickQuestionByCategory(category);
        System.out.println(question);
    }


    private Category getCategoryByPlace(int place) {
        return switch (place) {
            case 0, 4, 8 -> Category.Pop;
            case 1, 5, 9 -> Category.Science;
            case 2, 6, 10 -> Category.Sports;
            default -> Category.Rock;
        };
    }

    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = playerBase.get(turn);

        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.incrementPurse();
                System.out.println(currentPlayer.getPlayerName()
                        + " now has "
                        + currentPlayer.getPurse()
                        + " Gold Coins.");
                boolean winner = didPlayerWin(currentPlayer);
                updateTurn();
                return winner;
            } else {
                updateTurn();
                return true;
            }

        } else {
            System.out.println("Answer was corrent!!!!");
            currentPlayer.incrementPurse();
            System.out.println(currentPlayer.getPlayerName()
                    + " now has "
                    + currentPlayer.getPurse()
                    + " Gold Coins.");

            boolean winner = didPlayerWin(currentPlayer);
            updateTurn();
            return winner;
        }
    }

    public boolean wrongAnswer() {
        Player currentPlayer = playerBase.get(turn);
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getPlayerName() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);
        updateTurn();
        return true;
    }


    private boolean didPlayerWin(Player player) {
        return !(player.getPurse() == 6);
    }

    private void updateTurn() {
        turn++;
        if (turn == playerBase.size()) turn = 0;
    }
}
