public enum Dimensions {
    WIDTH(800),
    HEIGHT(800),
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
