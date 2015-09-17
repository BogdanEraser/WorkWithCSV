package MultyTasking;

/**
 * Created by Bogdan Kukharskiy on 10.09.2015.
 */
public class NewThread2 implements Runnable {
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("DDDДочерний поток " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            System.out.println("DDDдочерний поток прерван");
        }
        System.out.println("DDDдочерний поток завершен");
    }
}
