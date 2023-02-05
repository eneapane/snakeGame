import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Level3 extends AbstractLevel {
    private final Set<Pixel> wall;

    public Level3() {
        int numberOfSquaresHorizontally = Dimensions.WIDTH.totalLength()/Dimensions.WIDTH.squareLength();
        int numberOfSquaresVertically = Dimensions.HEIGHT.totalLength()/Dimensions.HEIGHT.squareLength();
        System.out.println(numberOfSquaresHorizontally);

        wall = new HashSet<>();
        for(int x = 0; x <= Dimensions.WIDTH.totalLength()/3; x += Dimensions.WIDTH.squareLength()){
            wall.add(new Pixel(x, 0));
            wall.add(new Pixel(x + (2*numberOfSquaresHorizontally / 3)*Dimensions.WIDTH.squareLength(), 0));
            wall.add(new Pixel(x, Dimensions.HEIGHT.totalLength() - Dimensions.HEIGHT.squareLength()));
            wall.add(new Pixel(x + (2*numberOfSquaresHorizontally / 3)*Dimensions.WIDTH.squareLength(),
                    Dimensions.HEIGHT.totalLength() - Dimensions.HEIGHT.squareLength()));
        }
        for(int y = 0; y <= Dimensions.HEIGHT.totalLength()/3; y += Dimensions.HEIGHT.squareLength()){
            wall.add(new Pixel(0, y));
            wall.add(new Pixel(0, y + (2*numberOfSquaresVertically / 3)*Dimensions.HEIGHT.squareLength()));
            wall.add(new Pixel(Dimensions.WIDTH.totalLength() - Dimensions.WIDTH.squareLength(), y));
            wall.add(new Pixel(Dimensions.WIDTH.totalLength() - Dimensions.WIDTH.squareLength(),
                    y + (2*numberOfSquaresVertically / 3)*Dimensions.HEIGHT.squareLength()));
        }
        for(int x = (numberOfSquaresHorizontally / 3)*Dimensions.WIDTH.squareLength();
            x <= (2 * numberOfSquaresHorizontally / 3)*Dimensions.WIDTH.squareLength(); x+= Dimensions.WIDTH.squareLength()){
            wall.add(new Pixel(x, (numberOfSquaresVertically / 3)*Dimensions.HEIGHT.squareLength()));
            wall.add(new Pixel(x, (2*numberOfSquaresVertically / 3)*Dimensions.HEIGHT.squareLength()));
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
