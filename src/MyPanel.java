import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyPanel extends JPanel
{
    private int squaresAlongWidth = Dimensions.SQUARES_ALONG_WIDTH.get();
    private int squaresAlongHeight = Dimensions.SQUARES_ALONG_HEIGHT.get();
    private int width = Dimensions.WIDTH.get();
    private int height = Dimensions.WIDTH.get();
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

    private static MyPanel single_instance;
    public static MyPanel getMyPanel(){
        if(single_instance == null)
            single_instance = new MyPanel(Snake.getSnake(), Dimensions.WIDTH.get(),
                    Dimensions.HEIGHT.get(), Dimensions.SQUARES_ALONG_WIDTH.get(),
                    Dimensions.SQUARES_ALONG_HEIGHT.get());
        return single_instance;
    }
    public MyPanel(Snake snake, int width, int height, int squaresAlongWidth, int squaresAlongHeight) {
        this.snake = snake;
        lastPressedChar = 'd';
        this.setApple(new Apple(0, 0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(getSnake().getLength() <= 5)
            drawGridLevel1(g);
        else
            drawGridLevel2(g);
        drawSnake(g);
        drawApple(g);
    }
    private void drawSnake(Graphics g)
    {
        g.setColor(Color.PINK);
        List<BodyPart> list = snake.getSnakeCoordinates();
        for (BodyPart bodyPart : list) {
            g.fillRect(bodyPart.x, bodyPart.y,
                    Dimensions.WIDTH.get()/ Dimensions.SQUARES_ALONG_WIDTH.get(),
                    Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get());
        }
    }
    private void drawApple(Graphics g)
    {
        g.setColor(Color.RED);
        if(apple.getX() == snake.getSnakeCoordinates().get(0).x &&
                apple.getY() == snake.getSnakeCoordinates().get(0).y) {
            apple = new Apple(ThreadLocalRandom.current().nextInt(Dimensions.SQUARES_ALONG_WIDTH.get()) * Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get(),
                    ThreadLocalRandom.current().nextInt(Dimensions.SQUARES_ALONG_HEIGHT.get()) * Dimensions.HEIGHT.get() / Dimensions.SQUARES_ALONG_HEIGHT.get());
            this.getSnake().addCoordinate(Dimensions.WIDTH.get(), Dimensions.HEIGHT.get(), Dimensions.SQUARES_ALONG_WIDTH.get(), Dimensions.SQUARES_ALONG_HEIGHT.get());
        }
        g.fillRect(apple.getX(), apple.getY(),
                Dimensions.WIDTH.get() / Dimensions.SQUARES_ALONG_WIDTH.get(),  Dimensions.HEIGHT.get()/Dimensions.SQUARES_ALONG_HEIGHT.get());
    }
    private void drawGridLevel1(Graphics g)
    {
        this.setBackground(Color.BLACK);
        for(int x = 0; x < Dimensions.HEIGHT.get(); x+= Dimensions.WIDTH.get()/Dimensions.SQUARES_ALONG_WIDTH.get())
        {
            g.setColor(Color.WHITE);
            g.drawLine(x, 0, x, this.getHeight());
        }
        for(int y = 0; y < Dimensions.HEIGHT.get(); y+=Dimensions.HEIGHT.get()/Dimensions.SQUARES_ALONG_HEIGHT.get())
            g.drawLine(0, y, this.getWidth(),y );
    }
    private void drawGridLevel2(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, Dimensions.WIDTH.get()/Dimensions.SQUARES_ALONG_WIDTH.get(), Dimensions.HEIGHT.get());
        g.fillRect(0, 0, Dimensions.WIDTH.get(), Dimensions.HEIGHT.get()/Dimensions.SQUARES_ALONG_HEIGHT.get());
        drawGridLevel1(g);
    }
}

