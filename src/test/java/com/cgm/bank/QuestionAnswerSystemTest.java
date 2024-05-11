package test.java.com.cgm.bank;

import main.java.com.cgm.bank.QuestionAnswerSystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Scanner;

public class QuestionAnswerSystemTest {

    @Test
    public void testAddQuestion() {
        QuestionAnswerSystem system = new QuestionAnswerSystem();

        // Add a question
        String questionToAdd = "What is Peter's favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
        String expectedOutput = "Question added successfully.\n";
        assertEquals(expectedOutput, normalizeLineEndings(getOutput(() -> system.addOrAskQuestion(questionToAdd))));
    }

    @Test
    public void testAskAddedQuestion() {
        QuestionAnswerSystem system = new QuestionAnswerSystem();

        // Add a question
        String questionToAdd = "What is Peter's favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
        system.addOrAskQuestion(questionToAdd);

        // Ask the added question
        String questionToAsk = "What is Peter's favorite food?";
        String expectedOutput = "Enter the question: Answers:\nPizza\nSpaghetti\nIce cream\n";
        assertEquals(expectedOutput, normalizeLineEndings(getOutput(() -> system.askQuestion(new Scanner(questionToAsk)))));
    }

    @Test
    public void testAskNonAddedQuestion() {
        QuestionAnswerSystem system = new QuestionAnswerSystem();

        // Ask the non-added question
        String questionToAskWithNegative = "When is Peters birthday?";
        String expectedOutput = "Enter the question: " + QuestionAnswerSystem.DEFAULT_ANSWER + "\n";
        assertEquals(expectedOutput, normalizeLineEndings(getOutput(() -> system.askQuestion(new Scanner(questionToAskWithNegative)))));
    }

    private String getOutput(Runnable runnable) {

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        runnable.run();
        System.setOut(System.out);

        return outContent.toString();
    }

    private String normalizeLineEndings(String text) {
        return text.replaceAll("\\r\\n", "\n");
    }
}
