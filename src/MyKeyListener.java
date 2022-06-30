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
            /*LinkedList<BodyPart> newList = new LinkedList<>();
            newList.add(new BodyPart(0, 0));
            for (int i = 0; i < panel.getSnake().getSnakeCoordinates().size() - 1; i++) {
                newList.add(panel.getSnake().getSnakeCoordinates().get(i));
            }*/
            if (e.getKeyChar() == 'w' && panel.getLastPressedChar() != 's' && panel.getLastPressedChar() != 'w') {
                panel.setLastPressedChar('w');
                /*int newX = (panel.getSnake().getSnakeCoordinates().get(0).x) % panel.getWidth();
                int newY = (panel.getSnake().getSnakeCoordinates().get(0).y - panel.getHeight() / panel.getSquaresAlongHeight()) % panel.getHeight();
                newList.set(0, new BodyPart(newX, newY));
                panel.getSnake().setSnakeCoordinates(newList);
                panel.repaint();*/
            } else if (e.getKeyChar() == 's' && panel.getLastPressedChar() != 'w' && panel.getLastPressedChar() != 's') {
                panel.setLastPressedChar('s');
                /*int newX = (panel.getSnake().getSnakeCoordinates().get(0).x) % panel.getWidth();
                int newY = (panel.getSnake().getSnakeCoordinates().get(0).y + panel.getHeight() / panel.getSquaresAlongHeight()) % panel.getHeight();
                newList.set(0, new BodyPart(newX, newY));
                panel.getSnake().setSnakeCoordinates(newList);
                panel.repaint();*/
            } else if (e.getKeyChar() == 'a' && panel.getLastPressedChar() != 'd' && panel.getLastPressedChar() != 'a') {
                panel.setLastPressedChar('a');
                /*int newX = panel.getSnake().getSnakeCoordinates().get(0).x - panel.getWidth() / panel.getSquaresAlongWidth();
                int newY = panel.getSnake().getSnakeCoordinates().get(0).y;
                newList.set(0, new BodyPart(newX, newY));
                panel.getSnake().setSnakeCoordinates(newList);
                panel.repaint();*/
            } else if (e.getKeyChar() == 'd' && panel.getLastPressedChar() != 'a' && panel.getLastPressedChar() != 'd') {
                panel.setLastPressedChar('d');
                /*int newX = panel.getSnake().getSnakeCoordinates().get(0).x + panel.getWidth() / panel.getSquaresAlongWidth();
                int newY = panel.getSnake().getSnakeCoordinates().get(0).y;
                newList.set(0, new BodyPart(newX, newY));
                panel.getSnake().setSnakeCoordinates(newList);
                panel.repaint();*/
            }
            runnable = new MyRunnable(panel);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}