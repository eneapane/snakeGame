import java.util.*;

//TODO make use of Dimensions.*
public class MyRunnable implements Runnable {
    private final Snake snake;
    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return !this.doStop;
    }

    public MyRunnable(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void run() {
        while (keepRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
            List<BodyPart> newList = snake.getSnakeCoordinates();
            newList.remove(newList.size() - 1);
            int newX = 0;
            int newY = 0;
            if (snake.getLastPressedChar() == 'w') {
                newX = snake.getSnakeCoordinates().get(0).x;
                newY = (snake.getSnakeCoordinates().get(0).y - Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get()
                        + Dimensions.HEIGHT.get()) % Dimensions.HEIGHT.get();
            } else if (snake.getLastPressedChar() == 's') {
                newX = (snake.getSnakeCoordinates().get(0).x) % Dimensions.WIDTH.get();
                newY = (snake.getSnakeCoordinates().get(0).y + Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get())
                        % Dimensions.HEIGHT.get();

            } else if (snake.getLastPressedChar() == 'a') {
                newX = (snake.getSnakeCoordinates().get(0).x
                    - Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get() + Dimensions.WIDTH.get())
                    % Dimensions.WIDTH.get();
                newY = snake.getSnakeCoordinates().get(0).y;
            } else if (snake.getLastPressedChar() == 'd') {
                newX = (snake.getSnakeCoordinates().get(0).x
                    + Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get()) % Dimensions.WIDTH.get();
                newY = snake.getSnakeCoordinates().get(0).y;
            }
            newList.add(0, new BodyPart(newX, newY));
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
