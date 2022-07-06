import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyPanel extends JPanel
{
    public int width;
    public int height;
    public int squaresAlongHeight;
    public int squaresAlongWidth;
    private Apple apple;
    private final Snake snake;
    private char lastPressedChar;

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

    /*public void setSnake(Snake snake) {
        this.snake = snake;
    }*/

    @Override
    public int getWidth() {
        return width;
    }

    /*public void setWidth(int width) {
        this.width = width;
    }*/

    @Override
    public int getHeight() {
        return height;
    }

    /*public void setHeight(int height) {
        this.height = height;
    }*/

    public int getSquaresAlongHeight() {
        return squaresAlongHeight;
    }

    /*public void setSquaresAlongHeight(int squaresAlongHeight) {
        this.squaresAlongHeight = squaresAlongHeight;
    }*/

    public int getSquaresAlongWidth() {
        return squaresAlongWidth;
    }

    /*public void setSquaresAlongWidth(int squaresAlongWidth) {
        this.squaresAlongWidth = squaresAlongWidth;
    }*/


    private static MyPanel single_instance;
    public static MyPanel getMyPanel(){
        if(single_instance == null)
            single_instance = new MyPanel(Snake.getSnake(), Dimensions.WIDTH.get(),
                    Dimensions.HEIGHT.get(), Dimensions.SQUARES_ALONG_WIDTH.get(),
                    Dimensions.SQUARES_ALONG_HEIGHT.get());
        return single_instance;
    }
    public MyPanel(Snake snake, int width, int height, int squaresAlongWidth, int squaresAlongHeight) {
        this.width = width;
        this.height = height;
        this.squaresAlongHeight = squaresAlongHeight;
        this.squaresAlongWidth = squaresAlongWidth;
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
    private void drawSnake(Graphics g)
    {
        g.setColor(Color.PINK);
        List<BodyPart> list = snake.getSnakeCoordinates();
        for (BodyPart bodyPart : list) {
            g.fillRect(bodyPart.x, bodyPart.y, width / squaresAlongWidth, height / squaresAlongHeight);
        }
    }
    private void drawApple(Graphics g)
    {
        g.setColor(Color.RED);
        if(apple.getX() == snake.getSnakeCoordinates().get(0).x &&
                apple.getY() == snake.getSnakeCoordinates().get(0).y) {
            apple = new Apple(ThreadLocalRandom.current().nextInt(squaresAlongWidth) * width / squaresAlongWidth,
                    ThreadLocalRandom.current().nextInt(squaresAlongHeight) * height / squaresAlongHeight);
            this.getSnake().addCoordinate(width, height, squaresAlongWidth, squaresAlongHeight);
        }
        g.fillRect(apple.getX(), apple.getY(), width/squaresAlongWidth, height/squaresAlongHeight);
    }
    private void drawGrid(Graphics g)
    {
        this.setBackground(Color.BLACK);
        for(int x = 0; x < this.getWidth(); x+= this.getWidth()/this.getSquaresAlongWidth())
        {
            g.setColor(Color.WHITE);
            g.drawLine(x, 0, x, this.getHeight());
        }
        for(int y = 0; y < this.getHeight(); y+=this.getHeight()/this.getSquaresAlongHeight())
            g.drawLine(0, y, this.getWidth(),y );
    }
}

