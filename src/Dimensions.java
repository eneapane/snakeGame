import java.awt.*;

public enum Dimensions {
    WIDTH(25),
    HEIGHT(25
    );

    private final int totalLength;
    private final int numberOfSquares;

    Dimensions(int numberOfSquares) {
        this.numberOfSquares = numberOfSquares;
        double screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() * 0.98;
        double difference = screenHeight % numberOfSquares;
        this.totalLength = (int) (screenHeight - difference);
    }

    int totalLength() {
        return totalLength;
    }

    int numberOfSquares() {
        return numberOfSquares;
    }

    int squareLength() {
        return totalLength / numberOfSquares;
    }
}
