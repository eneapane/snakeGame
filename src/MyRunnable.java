import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//TODO make use of Dimensions.*
public class MyRunnable implements Runnable {
    private final MyPanel panel;
    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return !this.doStop;
    }

    public MyRunnable(MyPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (keepRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
            LinkedList<BodyPart> newList = new LinkedList<>();
            newList.add(new BodyPart(0, 0));
            for (int i = 0; i < panel.getSnake().getSnakeCoordinates().size() - 1; i++) {
                newList.add(panel.getSnake().getSnakeCoordinates().get(i));
            }
            if (panel.getLastPressedChar() == 'w') {
                int newX = panel.getSnake().getSnakeCoordinates().get(0).x;
                int newY = (panel.getSnake().getSnakeCoordinates().get(0).y - Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get()
                        + Dimensions.HEIGHT.get()) % Dimensions.HEIGHT.get();
                newList.set(0, new BodyPart(newX, newY));
                panel.getSnake().setSnakeCoordinates(newList);
                panel.repaint();
            } else if (panel.getLastPressedChar() == 's') {
                int newX = (panel.getSnake().getSnakeCoordinates().get(0).x) % Dimensions.WIDTH.get();
                int newY = (panel.getSnake().getSnakeCoordinates().get(0).y + Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get())
                        % Dimensions.HEIGHT.get();
                newList.set(0, new BodyPart(newX, newY));
                panel.getSnake().setSnakeCoordinates(newList);
                panel.repaint();
            } else if (panel.getLastPressedChar() == 'a') {
                int newX = (panel.getSnake().getSnakeCoordinates().get(0).x
                        - Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get() + Dimensions.WIDTH.get())
                        % Dimensions.WIDTH.get();
                int newY = panel.getSnake().getSnakeCoordinates().get(0).y;
                newList.set(0, new BodyPart(newX, newY));
                panel.getSnake().setSnakeCoordinates(newList);
                panel.repaint();
            } else if (panel.getLastPressedChar() == 'd') {
                int newX = (panel.getSnake().getSnakeCoordinates().get(0).x
                        + Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get()) % Dimensions.WIDTH.get();
                int newY = panel.getSnake().getSnakeCoordinates().get(0).y;
                newList.set(0, new BodyPart(newX, newY));
                panel.getSnake().setSnakeCoordinates(newList);
                panel.repaint();
            }
            if (!linearSearch(panel.getSnake().getSnakeCoordinates())) {
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
