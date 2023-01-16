import java.awt.*;

public enum Dimensions {
    WIDTH(650, 25),
    HEIGHT(650, 25);


    private final int totalLength;
    private final int numberOfSquares;

    Dimensions(int totalLength, int numberOfSquares){
        this.totalLength = totalLength;
        this.numberOfSquares = numberOfSquares;
    }

    int totalLength(){
        return totalLength;
    }

    int numberOfSquares(){
        return numberOfSquares;
    }
    int squareLength(){
        return totalLength/numberOfSquares;
    }
}
