import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String [] args)
    {
        JFrame frame= new JFrame();
        frame.setBounds(0, 0, (int) (Dimensions.WIDTH.get()*1.1), (int) (Dimensions.HEIGHT.get()*1.1));
        frame.add(MyPanel.getMyPanel());

        frame.addKeyListener(new MyKeyListener(MyPanel.getMyPanel()));
        frame.setVisible(true);

    }
}
