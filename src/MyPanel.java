import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class MyPanel extends JPanel {
    private char lastPressedChar;
    private Apple apple;
    private final Snake snake;
    private int rectangleWidth = Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get();
    private int rectangleHeight = Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get();

    public char getLastPressedChar() {
        return lastPressedChar;
    }

    public void setLastPressedChar(char lastPressedChar) {
        this.lastPressedChar = lastPressedChar;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Snake getSnake() {
        return snake;
    }

    private static MyPanel single_instance;

    public static MyPanel getMyPanel() {
        if (single_instance == null)
            single_instance = new MyPanel(Snake.getSnake());
        return single_instance;
    }

    public MyPanel(Snake snake) {
        this.snake = snake;
        lastPressedChar = 'd';
        this.setApple(new Apple(0, 0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawGrid(g);

        drawSnake(g);
        drawApple(g);
    }

    private void drawSnake(Graphics g) {

        g.setColor(Color.GREEN);
        List<BodyPart> list = snake.getSnakeCoordinates();
        for (BodyPart bodyPart : list)
            g.fillRect(bodyPart.x, bodyPart.y, rectangleWidth, rectangleHeight);
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        if (apple.getX() == snake.getSnakeCoordinates().get(0).x &&
                apple.getY() == snake.getSnakeCoordinates().get(0).y) {
            int randomXRectangle = ThreadLocalRandom.current().nextInt(Dimensions.SQUARES_ALONG_WIDTH.get());
            int randomYRectangle = ThreadLocalRandom.current().nextInt(Dimensions.SQUARES_ALONG_HEIGHT.get());
            apple = new Apple( randomXRectangle * rectangleWidth, randomYRectangle * rectangleHeight);
            snake.addCoordinate(Dimensions.WIDTH.get(), Dimensions.HEIGHT.get(), Dimensions.SQUARES_ALONG_WIDTH.get(), Dimensions.SQUARES_ALONG_HEIGHT.get());
        }
        g.fillOval(apple.getX(), apple.getY(), rectangleWidth, rectangleHeight);
    }

    private void drawGrid(Graphics g) {
        this.setBackground(Color.BLACK);
        for (int x = 0; x <= Dimensions.WIDTH.get(); x += Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get()) {
            g.setColor(Color.WHITE);
            g.drawLine(x, 0, x, Dimensions.HEIGHT.get());
        }
        for (int y = 0; y <= Dimensions.HEIGHT.get(); y += Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get())
            g.drawLine(0, y, Dimensions.WIDTH.get(), y);
    }

}

