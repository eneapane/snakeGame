import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//this class makes the snake longer and registers the keyboard buttons
public class Snake extends LinkedList<BodyPart> {
    private static Snake single_instance = null;
    private int length;
    private final List<BodyPart> snakeCoordinates;
    private final int rectangleHeight = Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get();
    private final int rectangleWidth = Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get();

    public static Snake getSnake() {
        if (single_instance == null)
            single_instance = new Snake();
        return single_instance;
    }

    private Snake() {
        this.length = 3;
        int yC = ThreadLocalRandom.current().nextInt(Dimensions.SQUARES_ALONG_HEIGHT.get()) * rectangleHeight;
        int xC = ThreadLocalRandom.current().nextInt(2, Dimensions.SQUARES_ALONG_WIDTH.get()) * rectangleWidth;
        snakeCoordinates = new ArrayList<>();
        snakeCoordinates.add(new BodyPart(xC, yC));
        snakeCoordinates.add(new BodyPart(xC - rectangleWidth, yC));
        snakeCoordinates.add(new BodyPart(xC - 2 * rectangleHeight, yC));
    }

    public List<BodyPart> getSnakeCoordinates() {
        return snakeCoordinates;
    }

    public void addCoordinate() {
        this.length++;
        int size = snakeCoordinates.size();
        if (this.snakeCoordinates.get(size - 1).x == this.snakeCoordinates.get(size - 2).x) {
            int newX = this.snakeCoordinates.get(size - 1).x;
            int newY;
            if (this.snakeCoordinates.get(size - 1).y > this.snakeCoordinates.get(size - 2).y) {
                newY = this.snakeCoordinates.get(size - 1).y + rectangleHeight;
            } else {
                newY = this.snakeCoordinates.get(size - 1).y - rectangleHeight;
            }
            this.snakeCoordinates.add(new BodyPart(newX, newY));
        } else if (this.snakeCoordinates.get(size - 1).y == this.snakeCoordinates.get(size - 2).y) {
            int newY = this.snakeCoordinates.get(size - 1).y;
            int newX;
            if (this.snakeCoordinates.get(size - 1).x > this.snakeCoordinates.get(size - 2).x) {
                newX = this.snakeCoordinates.get(size - 1).x + rectangleWidth;
            } else {
                newX = this.snakeCoordinates.get(size - 1).x - rectangleWidth;
            }
            this.snakeCoordinates.add(new BodyPart(newX, newY));
        }
    }

    public void moveCoordinate(char characterCommand
    ) {
        //snake move coordinate
        snakeCoordinates.remove(snakeCoordinates.size() - 1);
        int newX = 0;
        int newY = 0;
        if (characterCommand == 'w') {
            newX = snakeCoordinates.get(0).x;
            newY = (snakeCoordinates.get(0).y - rectangleHeight
                    + Dimensions.HEIGHT.get()) % Dimensions.HEIGHT.get();
        } else if (characterCommand == 's') {
            newX = (snakeCoordinates.get(0).x) % Dimensions.WIDTH.get();
            newY = (snakeCoordinates.get(0).y + rectangleHeight)
                    % Dimensions.HEIGHT.get();

        } else if (characterCommand == 'a') {
            newX = (snakeCoordinates.get(0).x
                    - rectangleWidth + Dimensions.WIDTH.get())
                    % Dimensions.WIDTH.get();
            newY = snakeCoordinates.get(0).y;
        } else if (characterCommand == 'd') {
            newX = (snakeCoordinates.get(0).x
                    + rectangleWidth) % Dimensions.WIDTH.get();
            newY = snakeCoordinates.get(0).y;
        }
        snakeCoordinates.add(0, new BodyPart(newX, newY));
    }
}
