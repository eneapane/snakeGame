import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

class MyKeyListener extends KeyAdapter {
    private boolean firstKeyTypedCall;

    private final Snake snake;
    private char lastPressedChar = 'd';

    private MyRunnable currentRunnableObject;

    MyKeyListener(Snake snake) {
        this.snake = snake;
        firstKeyTypedCall = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (checkIfValidKey(e) && checkIfItGoesSameDirection(e) && checkIfItGoesOppositeDirection(e)) {
            stopCurrentRunningThread();

            changeDirectionOverhead();

            if (checkIfItGoesOppositeDirection(e)) {
                lastPressedChar = e.getKeyChar();

                startNewRunningThread();
            }
        }
    }

    private void stopCurrentRunningThread(){
        if (!firstKeyTypedCall)
            currentRunnableObject.doStop();
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
        currentRunnableObject = new MyRunnable(this.snake, this.lastPressedChar);
        Thread thread = new Thread(currentRunnableObject);
        thread.start();
    }

    private class MyRunnable implements Runnable {
        private Snake snake;
        private boolean doStop = false;
        private char lastPressedCharacter;

        public synchronized void doStop() {
            doStop = true;
        }

        private synchronized boolean keepRunning() {
            return !doStop;
        }

        private MyRunnable() {

        }

        public MyRunnable(Snake snake, char lastPressedCharacter) {
            this.snake = snake;
            this.lastPressedCharacter = lastPressedCharacter;
        }

        @Override
        public void run() {
            while (keepRunning()) {
                try {
                    Thread.sleep(100);
                    snake.moveCoordinate(lastPressedCharacter);
                    MyPanel.getMyPanel().repaint();
                } catch (InterruptedException | SnakeBitItselfException exc) {
                    exc.printStackTrace();
                    System.err.println(exc.getMessage());
                    System.exit(0);
                }
            }
        }
    }
}