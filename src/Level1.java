import java.awt.*;
import java.util.HashSet;
import java.util.Set;

class Level1 extends AbstractLevel{

    private final Set<Pixel> wall;

    public Level1() {
        wall = new HashSet<>();
    }

    public Set<Pixel> getWall() {
        return wall;
    }

    @Override
    protected void drawWall(Graphics g) {

    }

}
