import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Snake extends LinkedList<BodyPart> {
    private List<BodyPart> snakeCoordinates;
    private int length;
    private static Snake single_instance = null;
    private final int rectangleHeight = Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get();
    private final int rectangleWidth = Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get();
    private char lastPressedChar;

    public char getLastPressedChar() {
        return lastPressedChar;
    }

    public void setLastPressedChar(char lastPressedChar) {
        this.lastPressedChar = lastPressedChar;
    }

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
        lastPressedChar = 'd';
    }

    public List<BodyPart> getSnakeCoordinates() {return snakeCoordinates;}
    public void setSnakeCoordinates(ArrayList<BodyPart> snakeCoordinates) {
        this.snakeCoordinates = snakeCoordinates;
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
}
