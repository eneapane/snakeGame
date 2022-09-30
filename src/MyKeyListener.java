import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

class MyKeyListener extends KeyAdapter
{
    private boolean firstKeyTypedCall;
    private final Snake snake;
    private MyRunnable runnable;
    public MyKeyListener(Snake snake)
    {
        this.snake = snake;
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

            if (e.getKeyChar() == 'w' && snake.getLastPressedChar() != 's' && snake.getLastPressedChar() != 'w') {
                snake.setLastPressedChar('w');
            } else if (e.getKeyChar() == 's' && snake.getLastPressedChar() != 'w' && snake.getLastPressedChar() != 's') {
                snake.setLastPressedChar('s');
            } else if (e.getKeyChar() == 'a' && snake.getLastPressedChar() != 'd' && snake.getLastPressedChar() != 'a') {
                snake.setLastPressedChar('a');
            } else if (e.getKeyChar() == 'd' && snake.getLastPressedChar() != 'a' && snake.getLastPressedChar() != 'd') {
                snake.setLastPressedChar('d');
            }
            runnable = new MyRunnable(snake);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}