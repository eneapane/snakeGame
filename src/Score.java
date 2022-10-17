import javax.swing.*;
import java.awt.*;

public class Score extends JFrame {
    private static Score single_instance;
    public static Score getScore(){
        if(single_instance == null)
            single_instance = new Score();
        return single_instance;
    }
    private Score(){
        //this.setBounds();
    }

    @Override
    public void paint(Graphics g) {
//        super.paint(g);
    }
}
