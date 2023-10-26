package edu.project1;

interface GameInterface extends AutoCloseable {
    void showRules();

    char askLetter() throws RuntimeException;

    void typeShadowedWord(String shadowedWord);

    void notifyWrongGuess(int amountMistakes, int maxMistakes);

    void notifyRightGuess();

    void notifyWin();

    void notifyLose();

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
