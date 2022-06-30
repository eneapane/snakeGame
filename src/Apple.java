import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Apple{
    private final int x;
    private final int y;
    public Apple( int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
