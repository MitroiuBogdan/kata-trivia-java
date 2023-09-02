package trivia;

import java.util.LinkedList;
import java.util.List;

public class QuestionDeck {

    private static final String POP_QUESTION_TEMPLATE = "Pop Question ";
    private static final String SCIENCE_QUESTION_TEMPLATE = "Science Question ";
    private static final String SPORTS_QUESTION_TEMPLATE = "Sports Question ";
    private static final String ROCK_QUESTION_TEMPLATE = "Rock Question ";

    private final LinkedList<String> popQuestions;
    private final LinkedList<String> scienceQuestions;
    private final LinkedList<String> sportsQuestions;
    private final LinkedList<String> rockQuestions;

    public QuestionDeck() {
        popQuestions = new LinkedList<>();
        scienceQuestions = new LinkedList<>();
        sportsQuestions = new LinkedList<>();
        rockQuestions = new LinkedList<>();
        loadQuestions();
    }

    public void loadQuestions() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(POP_QUESTION_TEMPLATE + i);
            scienceQuestions.addLast((SCIENCE_QUESTION_TEMPLATE + i));
            sportsQuestions.addLast((SPORTS_QUESTION_TEMPLATE + i));
            rockQuestions.addLast(ROCK_QUESTION_TEMPLATE + i);
        }
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

    public String pickQuestionByCategory(Category category) {
        return switch (category) {
            case Rock -> rockQuestions.removeFirst();
            case Science -> scienceQuestions.removeFirst();
            case Pop -> popQuestions.removeFirst();
            case Sports -> sportsQuestions.removeFirst();
        };
    }
}
