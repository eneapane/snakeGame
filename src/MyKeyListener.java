import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

class MyKeyListener extends KeyAdapter {
    private boolean firstKeyTypedCall;

    private char lastPressedChar = 'd';

    MyKeyListener(Snake snake) {
        MyRunnable.setSnake(snake);
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
            MyRunnable.doStop();
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
        MyRunnable.setLastPressedCharacter(lastPressedChar);
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }

    private static class MyRunnable implements Runnable {
        private static Snake snake;
        private static boolean doStop = false;
        private static char lastPressedCharacter;

        public static synchronized void doStop() {
            doStop = true;
        }

        private synchronized boolean keepRunning() {
            return !doStop;
        }

        private MyRunnable() {

        }

         private static void setSnake(Snake snake) {
            MyRunnable.snake = snake;
        }

         private static void setLastPressedCharacter(char lastPressedCharacter) {
            MyRunnable.lastPressedCharacter = lastPressedCharacter;
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