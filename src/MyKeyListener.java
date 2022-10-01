import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

class MyKeyListener extends KeyAdapter {
    private boolean firstKeyTypedCall;
    private final Snake snake;
    private MyRunnable runnable;
    private char lastPressedChar = 'd';

    public MyKeyListener(Snake snake) {
        this.snake = snake;
        firstKeyTypedCall = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 's' || e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
            if (!firstKeyTypedCall)
                runnable.doStop();
            try {
                Thread.sleep(25);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
            firstKeyTypedCall = false;

            if (checkIfItGoesOppositeDirection(e))
                lastPressedChar = e.getKeyChar();
            runnable = new MyRunnable(snake, lastPressedChar);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
    private boolean checkIfItGoesOppositeDirection(KeyEvent e){
        return !(lastPressedChar == 'a' && e.getKeyChar() == 'd') &&
                !(lastPressedChar == 'd' && e.getKeyChar() == 'a') &&
                !(lastPressedChar == 'w' && e.getKeyChar() == 's') &&
                !(lastPressedChar == 's' && e.getKeyChar() == 'w');
    }
}