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
            ArrayList<BodyPart> newList = new ArrayList<>();
            newList.add(new BodyPart(0, 0));
            for (int i = 0; i < snake.getSnakeCoordinates().size() - 1; i++) {
                newList.add(snake.getSnakeCoordinates().get(i));
            }
            if (snake.getLastPressedChar() == 'w') {
                int newX = snake.getSnakeCoordinates().get(0).x;
                int newY = (snake.getSnakeCoordinates().get(0).y - Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get()
                        + Dimensions.HEIGHT.get()) % Dimensions.HEIGHT.get();
                newList.set(0, new BodyPart(newX, newY));
                snake.setSnakeCoordinates(newList);
                MyPanel.getMyPanel().repaint();
            } else if (snake.getLastPressedChar() == 's') {
                int newX = (snake.getSnakeCoordinates().get(0).x) % Dimensions.WIDTH.get();
                int newY = (snake.getSnakeCoordinates().get(0).y + Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get())
                        % Dimensions.HEIGHT.get();
                newList.set(0, new BodyPart(newX, newY));
                snake.setSnakeCoordinates(newList);
                MyPanel.getMyPanel().repaint();
            } else if (snake.getLastPressedChar() == 'a') {
                int newX = (snake.getSnakeCoordinates().get(0).x
                        - Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get() + Dimensions.WIDTH.get())
                        % Dimensions.WIDTH.get();
                int newY = snake.getSnakeCoordinates().get(0).y;
                newList.set(0, new BodyPart(newX, newY));
                snake.setSnakeCoordinates(newList);
                MyPanel.getMyPanel().repaint();
            } else if (snake.getLastPressedChar() == 'd') {
                int newX = (snake.getSnakeCoordinates().get(0).x
                        + Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get()) % Dimensions.WIDTH.get();
                int newY = snake.getSnakeCoordinates().get(0).y;
                newList.set(0, new BodyPart(newX, newY));
                snake.setSnakeCoordinates(newList);
                MyPanel.getMyPanel().repaint();
            }
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
