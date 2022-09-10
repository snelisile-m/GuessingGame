package za.game;

import java.util.Random;

public class Answer {
    private String value;

    public Answer(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        Answer otherAnswer = (Answer) obj;
        return this.value.equalsIgnoreCase(otherAnswer.toString());
    }

    public Answer getHint(Answer lastAnswer, char guess) {
        /***
         * gets the missing letters.
         * matched the guess with the missing letters
         * if matches than letters are added to the guessing word
         */
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.value.length(); i++) {
            if (guess == this.value.charAt(i)) {
                result.append(guess);
            } else {
                result.append(lastAnswer.toString().charAt(i));
            }
        }
        return new Answer(result.toString());
    }

    public boolean hasLetter(char letter) {
        return this.value.indexOf(letter) >= 0;
    }

    public Answer generateRandomHint() {
        /***
         * generates a character at a random place
         * hints on what the word might be
         */
        Random random = new Random();
        int randomIndex = random.nextInt(this.value.length());

        String noLetters = "_".repeat(this.value.length());
        return this.getHint( new Answer(noLetters),
                this.value.charAt(randomIndex));
    }

    public boolean isGoodGuess(Answer wordToGuess, char letter) {
        /***
         * checks if the word entered is correct or not
         */
        return wordToGuess.hasLetter(letter) && !this.hasLetter(letter);
    }

}
