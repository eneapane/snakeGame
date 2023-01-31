import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        AbstractLevel level1 = new Level1();
        frame.add(level1);
        frame.addKeyListener(new MyKeyListener(Snake.instanceOf(), level1));
        frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
