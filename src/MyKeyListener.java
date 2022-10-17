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
    public void keyTyped(KeyEvent e) {
        if (checkIfValidKey(e) && checkIfItGoesSameDirection(e) && checkIfItGoesOppositeDirection(e)) {
            stopCurrentRunningThread();

            changeDirectionOverhead();

            if (checkIfItGoesOppositeDirection(e))
                lastPressedChar = e.getKeyChar();

            startNewRunningThread();
        }
    }

    private void stopCurrentRunningThread(){
        if (!firstKeyTypedCall)
            runnable.doStop();
        firstKeyTypedCall = false;
    }

    private void changeDirectionOverhead(){
        try {
            Thread.sleep(30);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    private boolean checkIfValidKey(KeyEvent e) {
        return e.getKeyChar() == 'w' || e.getKeyChar() == 's' || e.getKeyChar() == 'a' || e.getKeyChar() == 'd';
    }

    private boolean checkIfItGoesOppositeDirection(KeyEvent e) {
        return !(lastPressedChar == 'a' && e.getKeyChar() == 'd') &&
                !(lastPressedChar == 'd' && e.getKeyChar() == 'a') &&
                !(lastPressedChar == 'w' && e.getKeyChar() == 's') &&
                !(lastPressedChar == 's' && e.getKeyChar() == 'w');
    }

    private boolean checkIfItGoesSameDirection(KeyEvent e) {
        return !(lastPressedChar == 'a' && e.getKeyChar() == 'a') &&
                !(lastPressedChar == 'd' && e.getKeyChar() == 'd') &&
                !(lastPressedChar == 's' && e.getKeyChar() == 's') &&
                !(lastPressedChar == 'w' && e.getKeyChar() == 'w');
    }

    private void startNewRunningThread(){
        runnable = new MyRunnable(snake, lastPressedChar);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}