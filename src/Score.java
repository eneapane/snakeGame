import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {
    private static Score single_instance;
    public static Score getScore(){
        if(single_instance == null)
            single_instance = new Score();
        return single_instance;
    }
    private Score(){
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        JLabel label1 = new JLabel("SCORE");
        label1.setBackground(Color.WHITE);
        //Set the position of the text, relative to the icon:
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        this.add(label1);
    }
}
