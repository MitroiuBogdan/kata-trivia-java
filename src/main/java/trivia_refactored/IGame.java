package trivia_refactored;

public interface IGame {

	boolean add(String playerName);

	void roll(int roll);

	boolean wasCorrectlyAnswered();

	boolean wrongAnswer();

}