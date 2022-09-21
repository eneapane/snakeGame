import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Snake extends LinkedList<BodyPart> {
    private List<BodyPart> snakeCoordinates;
    private int length;

    public int getLength() {
        return length;
    }
    private static Snake single_instance = null;
    public static Snake getSnake(){
        if (single_instance == null)
            single_instance = new Snake(Dimensions.WIDTH.get(), Dimensions.HEIGHT.get(),
                    Dimensions.SQUARES_ALONG_HEIGHT.get(), Dimensions.SQUARES_ALONG_WIDTH.get());
        return single_instance;
    }
    private Snake(int width, int height, int squaresAlongHeight, int squaresAlongWidth)
    {
        this.length = 3;
        int yC = ThreadLocalRandom.current().nextInt(squaresAlongHeight)*height/squaresAlongHeight;
        int xC = ThreadLocalRandom.current().nextInt(2, squaresAlongWidth)*width/squaresAlongWidth;
        snakeCoordinates = new ArrayList<>();
        snakeCoordinates.add(new BodyPart(xC, yC));
        snakeCoordinates.add(new BodyPart(xC - width/squaresAlongWidth, yC));
        snakeCoordinates.add(new BodyPart(xC - 2*width/squaresAlongWidth, yC));
    }

    public List<BodyPart> getSnakeCoordinates() {
        return snakeCoordinates;
    }

    public void setSnakeCoordinates(LinkedList<BodyPart> snakeCoordinates) {
        this.snakeCoordinates = snakeCoordinates;
    }

    public void addCoordinate(int width, int height, int squaresAlongWidth, int squaresAlongHeight)
    {
        this.length++;
        int size = getSnakeCoordinates().size();
        if(this.getSnakeCoordinates().get(size - 1).x == this.getSnakeCoordinates().get(size - 2).x)
        {
            int newX = this.getSnakeCoordinates().get(size - 1).x;
            if(this.getSnakeCoordinates().get(size - 1).y > this.getSnakeCoordinates().get(size - 2).y) {
                int newY = this.getSnakeCoordinates().get(size - 1).y + height/squaresAlongHeight;
                this.getSnakeCoordinates().add(new BodyPart(newX, newY));
            }
            else
            {
                int newY = this.getSnakeCoordinates().get(size - 1).y - height/squaresAlongHeight;
                this.getSnakeCoordinates().add(new BodyPart(newX, newY));
            }
        }
        else if(this.getSnakeCoordinates().get(size - 1).y == this.getSnakeCoordinates().get(size - 2).y)
        {
            int newY = this.getSnakeCoordinates().get(size - 1).y;
            if(this.getSnakeCoordinates().get(size - 1).x > this.getSnakeCoordinates().get(size - 2).x) {
                int newX = this.getSnakeCoordinates().get(size - 1).x + width/squaresAlongWidth;
                this.getSnakeCoordinates().add(new BodyPart(newX, newY));
            }
            else
            {
                int newX = this.getSnakeCoordinates().get(size - 1).x - width/squaresAlongWidth;
                this.getSnakeCoordinates().add(new BodyPart(newX, newY));
            }
        }
    }
}
