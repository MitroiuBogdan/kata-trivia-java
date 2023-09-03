package trivia_refactored;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class QuestionDeck {

    private static final String DEFAULT_POP_QUESTION = "Pop Question";
    private static final String DEFAULT_SCIENCE_QUESTION = "Science Question";
    private static final String DEFAULT_SPORTS_QUESTION = "Sports Question";
    private static final String DEFAULT_ROCK_QUESTION = "Rock Question";

    private final LinkedList<String> popQuestions;
    private final LinkedList<String> scienceQuestions;
    private final LinkedList<String> sportsQuestions;
    private final LinkedList<String> rockQuestions;


    public QuestionDeck(int size) {
        popQuestions = new LinkedList<>();
        scienceQuestions = new LinkedList<>();
        sportsQuestions = new LinkedList<>();
        rockQuestions = new LinkedList<>();

        initDecks(size);
    }

    private void initDecks(int size) {
        IntStream
                .range(0, size)
                .forEach(value -> {
                    popQuestions.addLast(DEFAULT_POP_QUESTION + value);
                    scienceQuestions.addLast(DEFAULT_SCIENCE_QUESTION + value);
                    sportsQuestions.addLast(DEFAULT_SPORTS_QUESTION + value);
                    rockQuestions.addLast(DEFAULT_ROCK_QUESTION + value);
                });
    }

    public LinkedList<String> getPopQuestions() {
        return popQuestions;
    }

    public LinkedList<String> getScienceQuestions() {
        return scienceQuestions;
    }

    public LinkedList<String> getSportsQuestions() {
        return sportsQuestions;
    }

    public LinkedList<String> getRockQuestions() {
        return rockQuestions;
    }


    public String selectQuestion(Category category) {
        return switch (category) {
            case Pop -> getPopQuestions().removeFirst();
            case Science -> getScienceQuestions().removeFirst();
            case Sports -> getSportsQuestions().removeFirst();
            case Rock -> getRockQuestions().removeFirst();
        };
    }
}
