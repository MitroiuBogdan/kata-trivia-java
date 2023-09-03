package trivia;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class QuestionDeck {

    private static final String POP_QUESTION_TEMPLATE = "Pop Question ";
    private static final String SCIENCE_QUESTION_TEMPLATE = "Science Question ";
    private static final String SPORTS_QUESTION_TEMPLATE = "Sports Question ";
    private static final String ROCK_QUESTION_TEMPLATE = "Rock Question ";

    private final LinkedList<String> popQuestions;
    private final LinkedList<String> scienceQuestions;
    private final LinkedList<String> sportsQuestions;
    private final LinkedList<String> rockQuestions;

    public QuestionDeck(int size) {
        popQuestions = new LinkedList<>();
        scienceQuestions = new LinkedList<>();
        sportsQuestions = new LinkedList<>();
        rockQuestions = new LinkedList<>();
        loadQuestions(size);
    }

    public void loadQuestions(int size) {
        IntStream.range(0, size).forEach(i -> {
            popQuestions.addLast(POP_QUESTION_TEMPLATE + i);
            scienceQuestions.addLast((SCIENCE_QUESTION_TEMPLATE + i));
            sportsQuestions.addLast((SPORTS_QUESTION_TEMPLATE + i));
            rockQuestions.addLast(ROCK_QUESTION_TEMPLATE + i);
        });
    }

    public String pickQuestionByPlace(int place) {
        Category category = getPlaceCategory(place);
        System.out.println("The category is " + category.name());
        return switch (category) {
            case Rock -> rockQuestions.removeFirst();
            case Science -> scienceQuestions.removeFirst();
            case Pop -> popQuestions.removeFirst();
            case Sports -> sportsQuestions.removeFirst();
        };
    }

    public Category getPlaceCategory(int place) {
        return switch (place) {
            case 0, 4, 8 -> Category.Pop;
            case 1, 5, 9 -> Category.Science;
            case 2, 6, 10 -> Category.Sports;
            default -> Category.Rock;
        };
    }
}
