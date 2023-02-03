import javax.swing.*;
import java.awt.*;

public class Context {
    private static Context context;
    private static AbstractLevel strategy;

    private static MyKeyListener keyListener;
    public static void start(){
        if(context == null)
            context = new Context();
        executeStrategy();
    }

    private Context(){
        strategy = new Level1();
        keyListener = new MyKeyListener(strategy);
    }

    private static void executeStrategy(){
        JFrame frame = new JFrame();
        frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.add(strategy);
        frame.addKeyListener(keyListener);
    }

    public static void refreshContext(){
        if(Snake.instanceOf().getLength() % 10 == 0){
            Snake.instanceOf().setLength(3);
            setStrategy(new Level2());
        }
    }

    private static void setStrategy(AbstractLevel strategy){
        Context.strategy = strategy;
        keyListener.setStrategy(strategy);
        executeStrategy();
    }
}
