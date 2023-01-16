import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(MyPanel.getMyPanel());
        frame.addKeyListener(new MyKeyListener(Snake.getSnake()));
        frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
