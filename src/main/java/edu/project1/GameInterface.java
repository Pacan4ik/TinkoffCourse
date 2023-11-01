package edu.project1;

interface GameInterface extends AutoCloseable {
    void showRules();

    char askLetter() throws RuntimeException;

    void typeWord(String shadowedWord);

    void notifyWrongGuess(int amountMistakes, int maxMistakes);

    void notifyRightGuess();

    void notifyGameOver(Game.GameState gameState);

    final class ForcedExitException extends RuntimeException {
        ForcedExitException(String message) {
            super(message);
        }

        ForcedExitException(Throwable cause) {
            super(cause);
        }

        ForcedExitException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
