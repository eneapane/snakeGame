import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
//my panel paints the game AND
class MyPanel extends JPanel {
    private Pixel apple;
    private final Snake snake;


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
        this.apple = new Pixel(0, 0);
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
        List<Pixel> list = snake.getSnakeCoordinates();
        for (Pixel pixel : list)
            g.fillRect(pixel.x(), pixel.y(), Dimensions.WIDTH.squareLength(), Dimensions.HEIGHT.squareLength());
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        if (apple.x() == snake.getSnakeCoordinates().get(0).x() &&
                apple.y() == snake.getSnakeCoordinates().get(0).y()) {
            int randomXRectangle = ThreadLocalRandom.current().nextInt(Dimensions.WIDTH.numberOfSquares());
            int randomYRectangle = ThreadLocalRandom.current().nextInt(Dimensions.HEIGHT.numberOfSquares());
            apple = new Pixel(randomXRectangle * Dimensions.WIDTH.squareLength(), randomYRectangle * Dimensions.HEIGHT.squareLength());
            snake.addCoordinate();
        }
        g.fillOval(apple.x(), apple.y(), Dimensions.WIDTH.squareLength(), Dimensions.HEIGHT.squareLength());
    }

    private void drawGrid(Graphics g) {
        this.setBackground(Color.BLACK);
        Color previousColor = g.getColor();
        g.setColor(Color.WHITE);
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();
        for (int x = 0; x <= Dimensions.WIDTH.totalLength(); x += Dimensions.WIDTH.squareLength())
            g.drawLine(x, 0, x, Dimensions.HEIGHT.totalLength());
        for (int y = 0; y <= Dimensions.HEIGHT.totalLength(); y += Dimensions.HEIGHT.squareLength())
            g.drawLine(0, y, Dimensions.WIDTH.totalLength(), y);
        g.setColor(previousColor);
    }

}

