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
        if (checkIfValidKey(e)) {
            if (!firstKeyTypedCall)
                runnable.doStop();
            firstKeyTypedCall = false;

            try {
                Thread.sleep(25);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }

            if (checkIfItGoesOppositeDirection(e))
                lastPressedChar = e.getKeyChar();
            runnable = new MyRunnable(snake, lastPressedChar);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
    private boolean checkIfValidKey(KeyEvent e){
        return e.getKeyChar() == 'w' || e.getKeyChar() == 's' || e.getKeyChar() == 'a' || e.getKeyChar() == 'd';
    }
    private boolean checkIfItGoesOppositeDirection(KeyEvent e){
        return !(lastPressedChar == 'a' && e.getKeyChar() == 'd') &&
                !(lastPressedChar == 'd' && e.getKeyChar() == 'a') &&
                !(lastPressedChar == 'w' && e.getKeyChar() == 's') &&
                !(lastPressedChar == 's' && e.getKeyChar() == 'w');
    }
}