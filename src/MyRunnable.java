import java.util.*;


public class MyRunnable implements Runnable {
    private final Snake snake;
    private boolean doStop = false;
    private final char lastPressedCharacter;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return !this.doStop;
    }

    public MyRunnable(Snake snake, char lastPressedCharacter) {
        this.snake = snake;
        this.lastPressedCharacter = lastPressedCharacter;
    }

    @Override
    public void run() {
        while (keepRunning()) {
            try {
                Thread.sleep(100);
                snake.moveCoordinate(lastPressedCharacter);
                MyPanel.getMyPanel().repaint();
            } catch (InterruptedException | SnakeBitItselfException exc) {
                exc.printStackTrace();
                System.err.println(exc.getMessage());
                System.exit(0);
            }
        }
    }
}
