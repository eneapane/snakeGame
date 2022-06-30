import javax.swing.*;
import java.awt.*;

public class Main {
    public static int height;
    public static int width;
    public static int squaresAlongHeight;
    public static int squaresAlongWidth;
    public static void main(String [] args)
    {
        JFrame frame= new JFrame();
        frame.setBounds(0, 0, (int) (width*1.1), (int) (height*1.1));
        MyPanel panel = new MyPanel(new Snake(width, height, squaresAlongHeight, squaresAlongWidth), width,
                height, squaresAlongWidth, squaresAlongHeight);
        panel.setApple(new Apple(0, 0));
        frame.add(panel);
        frame.addKeyListener(new MyKeyListener(panel));
        frame.setVisible(true);
    }
    static{
        width = 600;
        height = 600;
        squaresAlongHeight = 30;
        squaresAlongWidth = 30;
    }
}
