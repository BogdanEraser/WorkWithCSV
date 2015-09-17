package MultyTasking;

/**
 * Created by Bogdan Kukharskiy on 10.09.2015.
 */
public class NewThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Дочерний поток " + i);
                Thread.sleep(500);
            }
        }
        catch (InterruptedException ex) {
                System.out.println("дочерний поток прерван");
        }
        System.out.println("дочерний поток завершен");
        }
    }

