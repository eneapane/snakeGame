import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
//my panel paints the game AND
class MyPanel extends JPanel {
    private Apple apple;
    private final Snake snake;
    private final int rectangleWidth = Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get();
    private final int rectangleHeight = Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get();


    private static MyPanel single_instance;

    public static MyPanel getMyPanel() {

        if (single_instance == null)
            single_instance = new MyPanel();
        return single_instance;
    }

    public MyPanel() {
        JLabel score = new JLabel("SCORE");
        score.setSize(100, 100);
        this.add(score);
        this.snake = Snake.getSnake();
        this.apple = new Apple(0, 0);
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
            apple = new Apple(randomXRectangle * rectangleWidth, randomYRectangle * rectangleHeight);
            snake.addCoordinate();
        }
        g.fillOval(apple.getX(), apple.getY(), rectangleWidth, rectangleHeight);
    }

    private void drawGrid(Graphics g) {
        this.setBackground(Color.BLACK);
        Color previousColor = g.getColor();
        g.setColor(Color.WHITE);
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();
        for (int x = 0; x <= Dimensions.WIDTH.get(); x += rectangleWidth)
            g.drawLine(x, 0, x, Dimensions.HEIGHT.get());
        for (int y = 0; y <= Dimensions.HEIGHT.get(); y += rectangleHeight)
            g.drawLine(0, y, Dimensions.WIDTH.get(), y);
        g.setColor(previousColor);
    }

}

