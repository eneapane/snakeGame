import java.util.*;

//TODO make use of Dimensions.*
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
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
            snake.moveCoordinate(lastPressedCharacter);
            MyPanel.getMyPanel().repaint();
            if (!linearSearch(snake.getSnakeCoordinates())) {
                System.err.println("exit");
                System.exit(0);
            }
        }
    }

    private boolean linearSearch(List<BodyPart> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    if (list.get(i).x == list.get(j).x && list.get(i).y == list.get(j).y)
                        return false;
                }
            }
        }
        return true;
    }
}
