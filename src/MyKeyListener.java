import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

class MyKeyListener extends KeyAdapter
{
    private boolean firstKeyTypedCall;
    private final MyPanel panel;
    private MyRunnable runnable;
    public MyKeyListener(MyPanel panel)
    {
        this.panel = panel;
        firstKeyTypedCall = true;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'w' || e.getKeyChar() == 's' || e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
            if (!firstKeyTypedCall)
                runnable.doStop();
            try
            {
                Thread.sleep(25);
            }
            catch (InterruptedException exc)
            {
                exc.printStackTrace();
            }
            firstKeyTypedCall = false;

            if (e.getKeyChar() == 'w' && panel.getLastPressedChar() != 's' && panel.getLastPressedChar() != 'w') {
                panel.setLastPressedChar('w');
            } else if (e.getKeyChar() == 's' && panel.getLastPressedChar() != 'w' && panel.getLastPressedChar() != 's') {
                panel.setLastPressedChar('s');
            } else if (e.getKeyChar() == 'a' && panel.getLastPressedChar() != 'd' && panel.getLastPressedChar() != 'a') {
                panel.setLastPressedChar('a');
            } else if (e.getKeyChar() == 'd' && panel.getLastPressedChar() != 'a' && panel.getLastPressedChar() != 'd') {
                panel.setLastPressedChar('d');
            }
            runnable = new MyRunnable(panel);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}