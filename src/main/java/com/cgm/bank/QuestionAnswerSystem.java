package main.java.com.cgm.bank;

import java.util.*;

public class QuestionAnswerSystem {
    private static final int MAX_QUESTION_LENGTH = 255;
    private static final int MAX_ANSWER_LENGTH = 255;
    public static final String DEFAULT_ANSWER = "the answer to life, universe and everything is 42";

    private final Map<String, List<String>> questionsAndAnswers;

    public QuestionAnswerSystem() {
        this.questionsAndAnswers = new HashMap<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                int option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        askQuestion(scanner);
                        break;
                    case 2:
                        addQuestion(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            } else {
                addOrAskQuestion(input);
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Ask a specific question");
        System.out.println("2. Add a question and its answers");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public void askQuestion(Scanner scanner) {
        System.out.print("Enter the question: ");
        String question = scanner.nextLine();

        if (questionsAndAnswers.containsKey(question)) {
            List<String> answers = questionsAndAnswers.get(question);
            System.out.println("Answers:");
            for (String answer : answers) {
                System.out.println(answer);
            }
        } else {
            System.out.println(DEFAULT_ANSWER);
        }
    }

    private void addQuestion(Scanner scanner) {
        System.out.print("Adding a question (format: <question>? \"<answer1>\" \"<answer2>\" ...): ");
        String input = scanner.nextLine();
        addOrAskQuestion(input);
    }

    public void addOrAskQuestion(String input) {
        String[] parts = input.split("\\?");

        if (parts.length != 2) {
            System.out.println("Invalid input format. Please follow the format: <question>? \"<answer1>\" \"<answer2>\" ...");
            return;
        }

        String question = parts[0].trim();
        if (question.isEmpty()) {
            System.out.println("Question cannot be empty.");
            return;
        }

        if (question.length() > MAX_QUESTION_LENGTH) {
            System.out.println("Question exceeds maximum length.");
            return;
        }

        String[] answersArray = parts[1].trim().split("\"");

        List<String> answers = new ArrayList<>();
        for (String answer : answersArray) {
            if (!answer.isEmpty() && !answer.equals("\"") && !answer.equals(" ")) {
                String trimmedAnswer = answer.trim();
                if (trimmedAnswer.length() > MAX_ANSWER_LENGTH) {
                    System.out.println("Answer exceeds maximum length.");
                    return;
                }
                answers.add(trimmedAnswer);
            }
        }

        if (answers.isEmpty()) {
            System.out.println("At least one answer must be provided.");
            return;
        }

        questionsAndAnswers.put(question+"?", answers);
        System.out.println("Question added successfully.");
    }
}
