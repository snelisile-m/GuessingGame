package za.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class GuessingGame {

    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Answer wordToGuess = getWords(player);
        Answer currentAnswer = start(player, wordToGuess);
        String message = run(player, wordToGuess, currentAnswer);
        System.out.println(message);
    }

    private  static String run(Player player, Answer wordToGuess, Answer currentAnswer){
        /**
         * shows the result for each turn
         * if it was  a correct guess the program will terminate
         * if not will keep counting chances untill there are no more chances
         */
        while (!currentAnswer.equals( wordToGuess)) {
            String guess = player.getGuess();
            if (player.wantsToQuit()) {
                System.out.println("Bye!");
                break;
            }
            char guessedLetter = guess.charAt(0);
            if (currentAnswer.isGoodGuess(wordToGuess, guessedLetter)) {
                currentAnswer = wordToGuess.getHint(currentAnswer, guess.charAt(0));
                System.out.println(currentAnswer);
            } else {
                player.lostChance();
                System.out.println("Wrong! Number of guesses left: " + player.getChances());
                if (player.hasNoChances()) {
                    return "Sorry, you are out of guesses. The word was: " + wordToGuess;
                }
            }
        }

        return "That is correct! You escaped the noose .. this time.";
    }

    private static Answer start(Player player, Answer wordToGuess) {
        /**
         * prints a prompt message and
         * returns the generated guessing word
         */
        Answer currentAnswer = wordToGuess.generateRandomHint();
        System.out.println("Guess the word: " + currentAnswer);
        return currentAnswer;
    }

    private static Answer getWords(Player player) throws IOException {
        /**
         * reads the words from the text file specified by user
         */
        Random random = new Random();

        System.out.println("Words file? [leave empty to use short_words.txt]");
        String fileName = player.getWordsFile();
        List<String> words = Files.readAllLines(Path.of(fileName));

        int randomIndex = random.nextInt(words.size());
        String randomWord = words.get(randomIndex).trim();
        return new Answer(randomWord);
    }

}

/*
 * have added this comment for learning git issues
 */