import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Level2 extends AbstractLevel{
    private final Set<Pixel> wall;

    public Level2() {
        wall = new HashSet<>();
        for(int x = 0; x < Dimensions.WIDTH.totalLength(); x += Dimensions.WIDTH.squareLength()){
            wall.add(new Pixel(x, 0));
            wall.add(new Pixel(x, Dimensions.HEIGHT.totalLength() - Dimensions.HEIGHT.squareLength()));
        }
        for(int y = 0; y < Dimensions.HEIGHT.totalLength(); y += Dimensions.HEIGHT.squareLength()){
            wall.add(new Pixel(0, y));
            wall.add(new Pixel(Dimensions.WIDTH.totalLength() - Dimensions.WIDTH.squareLength(), y));
        }
    }

    public Set<Pixel> getWall() {
        return wall;
    }

    @Override
    protected void drawWall(Graphics g) {
        g.setColor(Color.ORANGE);
        for(Pixel block : wall){
            g.fillRect(block.x(), block.y(), Dimensions.WIDTH.squareLength(), Dimensions.HEIGHT.squareLength());
        }
    }
}
