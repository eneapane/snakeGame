import java.awt.*;

public enum Dimensions {
    WIDTH(650),
    HEIGHT(650),
    SQUARES_ALONG_WIDTH(25),
    SQUARES_ALONG_HEIGHT(25);

    private final int value;
    Dimensions(int value){
        this.value = value;
    }


    public int get(){
        return value;
    }
}
