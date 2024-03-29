import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//this class makes the snake longer and registers the keyboard buttons
class Snake {
    private static Snake single_instance = null;
    private int length;
    private List<Pixel> snakeCoordinates;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        List<Pixel> copyCoordinates = new ArrayList<>();
        for(int i = 0; i < length; i++){
            copyCoordinates.add(snakeCoordinates.get(i));
        }
        snakeCoordinates = copyCoordinates;
    }

    public static Snake instanceOf() {
        if (single_instance == null)
            single_instance = new Snake();
        return single_instance;
    }

    private Snake() {
        this.length = 3;
        int yC = ThreadLocalRandom.current().nextInt(Dimensions.HEIGHT.numberOfSquares()) * Dimensions.HEIGHT.squareLength();
        int xC = ThreadLocalRandom.current().nextInt(2, Dimensions.WIDTH.numberOfSquares()) * Dimensions.WIDTH.squareLength();
        snakeCoordinates = new ArrayList<>();
        snakeCoordinates.add(new Pixel(xC, yC));
        snakeCoordinates.add(new Pixel(xC - Dimensions.WIDTH.squareLength(), yC));
        snakeCoordinates.add(new Pixel(xC - 2 * Dimensions.HEIGHT.squareLength(), yC));
    }

    public List<Pixel> getSnakeCoordinates() {
        return snakeCoordinates;
    }

    public void addCoordinate() {
        this.length++;
        if (snakeIsMovingVertically()) {
            addVerticalCoordinate();
        } else if (snakeIsMovingHorizontally()) {
            addHorizontalCoordinate();
        }
    }

    private void addHorizontalCoordinate() {
        int newY = this.snakeCoordinates.get(snakeCoordinates.size() - 1).y();
        int newX;
        if (snakeIsMovingRight()) {
            newX = this.snakeCoordinates.get(snakeCoordinates.size() - 1).x() + Dimensions.WIDTH.squareLength();
        } else {
            newX = this.snakeCoordinates.get(snakeCoordinates.size() - 1).x() - Dimensions.WIDTH.squareLength();
        }
        this.snakeCoordinates.add(new Pixel(newX, newY));
    }

    private void addVerticalCoordinate() {
        int newX = this.snakeCoordinates.get(snakeCoordinates.size() - 1).x();
        int newY;
        if (snakeIsMovingUp()) {
            newY = this.snakeCoordinates.get(snakeCoordinates.size() - 1).y() + Dimensions.HEIGHT.squareLength();
        } else {
            newY = this.snakeCoordinates.get(snakeCoordinates.size() - 1).y() - Dimensions.HEIGHT.squareLength();
        }
        this.snakeCoordinates.add(new Pixel(newX, newY));
    }

    private boolean snakeIsMovingVertically() {
        return this.snakeCoordinates.get(snakeCoordinates.size() - 1).x() == this.snakeCoordinates.get(snakeCoordinates.size() - 2).x();
    }

    private boolean snakeIsMovingUp() {
        return this.snakeCoordinates.get(snakeCoordinates.size() - 1).y() > this.snakeCoordinates.get(snakeCoordinates.size() - 2).y();
    }

    private boolean snakeIsMovingHorizontally() {
        return this.snakeCoordinates.get(snakeCoordinates.size() - 1).y() == this.snakeCoordinates.get(snakeCoordinates.size() - 2).y();
    }

    private boolean snakeIsMovingRight() {
        return this.snakeCoordinates.get(snakeCoordinates.size() - 1).x() > this.snakeCoordinates.get(snakeCoordinates.size() - 2).x();
    }

    public void moveCoordinate(char characterCommand) {
        //snake move coordinate
        snakeCoordinates.remove(snakeCoordinates.size() - 1);
        int newX = 0;
        int newY = 0;
        if (characterCommand == 'w') {
            newX = snakeCoordinates.get(0).x();
            newY = (snakeCoordinates.get(0).y() - Dimensions.HEIGHT.squareLength()
                    + Dimensions.HEIGHT.totalLength()) % Dimensions.HEIGHT.totalLength();
        } else if (characterCommand == 's') {
            newX = (snakeCoordinates.get(0).x()) % Dimensions.WIDTH.totalLength();
            newY = (snakeCoordinates.get(0).y() + Dimensions.HEIGHT.squareLength())
                    % Dimensions.HEIGHT.totalLength();

        } else if (characterCommand == 'a') {
            newX = (snakeCoordinates.get(0).x()
                    - Dimensions.WIDTH.squareLength() + Dimensions.WIDTH.totalLength())
                    % Dimensions.WIDTH.totalLength();
            newY = snakeCoordinates.get(0).y();
        } else if (characterCommand == 'd') {
            newX = (snakeCoordinates.get(0).x()
                    + Dimensions.WIDTH.squareLength()) % Dimensions.WIDTH.totalLength();
            newY = snakeCoordinates.get(0).y();
        }
        Pixel newCoordinate = new Pixel(newX, newY);
        if(snakeCoordinates.contains(newCoordinate))
            throw new RuntimeException("Snake bit itself.");
        snakeCoordinates.add(0, newCoordinate);
    }
}
