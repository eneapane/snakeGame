import javax.swing.*;
import java.awt.*;

public class Context {

    private static class LevelTracker {
        private static final int maxLevel = 2;
        private static int currentLevel = 0;
        private static final AbstractLevel [] levels = {new Level1(), new Level2(), new Level3()};
    }
    private static Context context;
    private static AbstractLevel strategy;

    private static MyKeyListener keyListener;
    public static void start(){
        if(context == null)
            context = new Context();
        executeStrategy();
    }

    private Context(){
        strategy = LevelTracker.levels[LevelTracker.currentLevel];
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
        if(LevelTracker.currentLevel < LevelTracker.maxLevel) {
            if(Snake.instanceOf().getLength() % 4 == 0){
                Snake.instanceOf().setLength(3);
                LevelTracker.currentLevel++;
                setStrategy(LevelTracker.levels[LevelTracker.currentLevel]);
            }
        }
    }

    private static void setStrategy(AbstractLevel strategy){
        Context.strategy = strategy;
        keyListener.setStrategy(strategy);
        executeStrategy();
    }
}
